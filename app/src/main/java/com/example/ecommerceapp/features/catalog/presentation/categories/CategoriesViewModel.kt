package com.example.ecommerceapp.features.catalog.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.features.catalog.domain.usecase.GetCategoriesUseCase
import com.example.ecommerceapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor (
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CategoriesState())
    val state: StateFlow<CategoriesState> = _state.asStateFlow()

    init {
        getCategories()
    }

    fun onEvent(event: CategoriesEvent){
        when(event){
            is CategoriesEvent.OnCategorySelected -> {
                _state.value = _state.value.copy(
                    selectedCategory = event.category
                )
            }

            is CategoriesEvent.OnLoadCategories -> {
                getCategories()
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
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }
                    }
                }
        }
    }
}