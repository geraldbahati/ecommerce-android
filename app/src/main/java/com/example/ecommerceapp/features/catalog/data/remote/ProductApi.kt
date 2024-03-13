package com.example.ecommerceapp.features.catalog.data.remote

import com.example.ecommerceapp.features.catalog.data.remote.dto.PaginatedProductsResponse
import com.example.ecommerceapp.features.catalog.data.remote.dto.ProductDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("products/list")
    suspend fun getProducts(): Response<List<ProductDTO>>

    @GET("sub-categories/products/{subCategoryId}")
    suspend fun getProductsBySubCategory(
        @Path("subCategoryId") subCategoryId: String
    ): Response<PaginatedProductsResponse>
}