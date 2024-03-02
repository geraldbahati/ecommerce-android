package com.example.ecommerceapp.features.catalog.domain.repository

import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.example.ecommerceapp.util.Resource

interface CategoryRepository {
    suspend fun getCategories(): Resource<List<Category>>
    suspend fun getCategoryById(id: String): Resource<Category>
    suspend fun searchCategory(query: String): Resource<List<Category>>
    suspend fun refreshCategories(): Resource<List<Category>>
}