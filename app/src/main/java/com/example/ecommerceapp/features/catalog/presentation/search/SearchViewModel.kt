package com.example.ecommerceapp.features.catalog.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.example.ecommerceapp.features.catalog.domain.usecase.SearchCategoriesUseCase
import com.example.ecommerceapp.features.catalog.domain.usecase.SearchProductsUseCase
import com.example.ecommerceapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCategoriesUseCase: SearchCategoriesUseCase
) : ViewModel() {
    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState

    private var searchJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchQueryChange -> {
                _searchState.value = _searchState.value.copy(query = event.query)
                debounceSearch(event.query)
            }

            is SearchEvent.OnSearchCategory -> {
                searchJob?.cancel()
                search(event.query)
            }
            is SearchEvent.OnToggleSearch -> {
                _searchState.value = _searchState.value.copy(isSearching = event.isSearching)
            }
        }
    }

    private fun debounceSearch(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            search(query)
        }
    }

    private fun search(
        query: String = _searchState.value.query.lowercase()
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
                _searchState.value = _searchState.value.copy(
                    loadedCategories = result.data ?: emptyList(),
                    isLoadingCategories = false
                )
            }
            is Resource.Error -> {
                _searchState.value = _searchState.value.copy(
                    errorMessage = result.message ?: "An unexpected error occurred.",
                    isLoadingCategories = false
                )
            }
            is Resource.Loading -> {
                _searchState.value = _searchState.value.copy(isLoadingCategories = true)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

}