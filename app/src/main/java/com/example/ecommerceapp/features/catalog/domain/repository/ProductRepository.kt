package com.example.ecommerceapp.features.catalog.domain.repository

import com.example.ecommerceapp.features.catalog.domain.models.Product
import com.example.ecommerceapp.util.Resource

interface ProductRepository {
    suspend fun getProducts(): Resource<List<Product>>
    suspend fun getProductById(id: String): Resource<Product>
    suspend fun searchProduct(query: String): Resource<List<Product>>
    suspend fun refreshProducts(): Resource<List<Product>>
}