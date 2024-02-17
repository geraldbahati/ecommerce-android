package com.example.ecommerceapp.features.catalog.domain.usecase

import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.example.ecommerceapp.features.catalog.domain.repository.CategoryRepository
import com.example.ecommerceapp.util.Resource
import javax.inject.Inject

class GetCategoryByIdUseCase @Inject constructor(
    private val repository: CategoryRepository
){
    suspend operator fun invoke(id: String): Resource<Category> = repository.getCategoryById(id)
}