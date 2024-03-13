package com.example.ecommerceapp.features.catalog.domain.repository

import com.example.ecommerceapp.features.catalog.domain.models.SubCategory
import com.example.ecommerceapp.util.Resource

interface SubCategoryRepository {
    suspend fun getSubCategoriesByCategoryId(categoryId: String): Resource<List<SubCategory>>
}