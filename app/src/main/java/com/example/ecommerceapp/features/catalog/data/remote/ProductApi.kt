package com.example.ecommerceapp.features.catalog.data.remote

import com.example.ecommerceapp.features.catalog.data.remote.dto.ProductDTO
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("products/list")
    suspend fun getProducts(): Response<List<ProductDTO>>
}