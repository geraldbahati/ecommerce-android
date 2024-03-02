package com.example.ecommerceapp.features.catalog.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel(){
//    var state by mutableStateOf(HomeState())
//        private set
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        getCategories()
    }

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnCategorySelected -> {
                _state.value = _state.value.copy(
                    selectedCategory = event.category
                )
            }
            is HomeEvent.OnSetNavigator -> {
                _state.value = _state.value.copy(
                    navigator = event.navigator
                )
            }
            is HomeEvent.OnLoadCategories -> {
                getCategories()
            }
            is HomeEvent.OnNavigateToCart -> {
//                state.navigator?.navigate(Routes.CART)
            }
            is HomeEvent.OnNavigateToCategories -> {
//                state.navigator?.navigate(Routes.CATEGORIES)
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
                            result.data?.let { categories ->
                                val initialSelectedCategory = categories.firstOrNull()
                                _state.value = _state.value.copy(
                                    isLoading = false,
                                    loadedCategories = categories,
                                    selectedCategory = initialSelectedCategory
                                )
                            }
                        }
                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                categoriesErrorMessage = result.message
                            )
                        }
                    }
                }
        }
    }
}
