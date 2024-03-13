package com.example.ecommerceapp.features.catalog.presentation.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.example.ecommerceapp.features.catalog.domain.models.SubCategory
import com.example.ecommerceapp.features.catalog.domain.usecase.GetCategoriesUseCase
import com.example.ecommerceapp.features.catalog.domain.usecase.GetSubCategoriesByCategoryUseCase
import com.example.ecommerceapp.features.catalog.domain.usecase.SearchCategoriesUseCase
import com.example.ecommerceapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor (
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getSubCategoriesByCategoryUseCase: GetSubCategoriesByCategoryUseCase,
    private val searchCategoriesUseCase: SearchCategoriesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CategoriesState())
    val state: StateFlow<CategoriesState> = _state.asStateFlow()

    private var searchJob: Job? = null

    init {
        getCategories()
    }

    fun onEvent(event: CategoriesEvent){
        when(event){
            is CategoriesEvent.OnCategorySelected -> {

                if (_state.value.selectedCategory == event.category) {
                    return
                }

                _state.value = _state.value.copy(
                    selectedCategory = event.category
                )


                getSubCategories()
            }

            is CategoriesEvent.OnLoadCategories -> {
                getCategories()
            }

            // events for sub categories
            is CategoriesEvent.OnSubCategorySelected -> {
                _state.value = _state.value.copy(
                    selectedSubCategory = event.subCategory
                )
            }

            // search events
            is CategoriesEvent.OnSearchQueryChange -> {
                _state.value = _state.value.copy(query = event.query)
                debounceSearch(event.query)
                Log.i("SearchViewModel", "onEvent: ${_state.value.query}")
                Log.i("SearchViewModel", "onEvent: ${_state.value.loadedCategories.size}")
            }

            is CategoriesEvent.OnSearchCategory -> {
                searchJob?.cancel()
                search()
            }
            is CategoriesEvent.OnToggleSearch -> {
                _state.value = _state.value.copy(isSearching = event.isSearching)
            }

            is CategoriesEvent.OnSearchCategorySelected -> {
                if (_state.value.selectedSearchCategory == event.category) {
                    return
                }
                _state.value = _state.value.copy(
                    selectedSearchCategory = event.category
                )
                getSubCategories(_state.value.selectedSearchCategory?.id ?: "")
            }
        }
    }

    private fun getCategories(){
        viewModelScope.launch {
            getCategoriesUseCase()
                .collect {
                    result ->
                    when(result){
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(
                                isLoading = true
                            )
                        }
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                categories = result.data ?: emptyList()
                            )
                        }
                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                errorMessage = result.message ?: "An unexpected error occurred"
                            )
                        }
                    }
                }
        }
    }

    private fun getSubCategories(
        categoryId: String = _state.value.selectedCategory?.id ?: ""
    ) {
        viewModelScope.launch {
            getSubCategoriesByCategoryUseCase(categoryId)
                .collect { result ->
                    handleSubCategoriesResult(result)
                }
        }
    }

    private fun handleSubCategoriesResult(result: Resource<List<SubCategory>>) {
        Log.d("CategoryDetailViewModel", "Fetching subcategories result: ${result.data?.size ?: 0}")
        when (result) {
            is Resource.Loading -> {
                _state.value = _state.value.copy(
                    isLoading = true
                )
            }

            is Resource.Success -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    subCategories = result.data ?: emptyList(),
                    selectedSubCategory = result.data?.firstOrNull()
                )
            }

            is Resource.Error -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = result.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    private fun debounceSearch(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300L)
            search(query)
        }
    }

    private fun search(
        query: String = _state.value.query.lowercase()
    ) {
        viewModelScope.launch {
            searchCategoriesUseCase(query)
                .collect { result ->
                    handleSearchResult(result)
                }
        }
    }

    private fun handleSearchResult(result: Resource<List<Category>>) {
        when (result) {
            is Resource.Success -> {
                _state.value = _state.value.copy(
                    loadedCategories = result.data ?: emptyList(),
                    isLoadingCategories = false
                )
            }
            is Resource.Error -> {
                _state.value = _state.value.copy(
                    errorMessage = result.message ?: "An unexpected error occurred.",
                    isLoadingCategories = false
                )
            }
            is Resource.Loading -> {
                _state.value = _state.value.copy(isLoadingCategories = true)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
}