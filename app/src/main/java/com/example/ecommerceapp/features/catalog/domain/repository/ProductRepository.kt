package com.example.ecommerceapp.features.catalog.domain.repository

import com.example.ecommerceapp.features.catalog.domain.models.Product
import com.example.ecommerceapp.util.Resource

interface ProductRepository {
    suspend fun getProductsBySubCategory(subCategoryId: String): Resource<List<Product>>
}