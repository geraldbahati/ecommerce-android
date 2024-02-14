package com.example.ecommerceapp.features.catalog.data.remote


import com.example.ecommerceapp.features.catalog.data.remote.dto.CategoryDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CategoryApi {

    @GET("categories")
    suspend fun getCategories(
        @Header("Authorization") token: String? = null,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<List<CategoryDTO>>
}