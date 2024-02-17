package com.example.ecommerceapp.features.catalog.domain.usecase

import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.example.ecommerceapp.features.catalog.domain.repository.CategoryRepository
import com.example.ecommerceapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
){
    suspend operator fun invoke(): Flow<Resource<List<Category>>> {
        return flow {
            when (val categories = repository.getCategories()) {
                is Resource.Success -> {
                    emit(Resource.Success(categories.data))
                }
                is Resource.Error -> {
                    emit(Resource.Error(categories.message!!))
                }
                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
            }
        }
    }
}