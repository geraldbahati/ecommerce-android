package com.example.ecommerceapp.features.catalog.data.remote


import com.example.ecommerceapp.features.catalog.data.remote.dto.CategoryDTO
import com.example.ecommerceapp.features.catalog.data.remote.dto.GetCategoriesRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryApi {


    @GET("categories")
    suspend fun getPaginatedCategories(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<List<CategoryDTO>>

    @GET("categories")
    suspend fun getCategories(): Response<GetCategoriesRequest>

    @GET("categories/search")
    suspend fun searchCategories(
        @Query("name") query: String
    ): Response<GetCategoriesRequest>

    @GET("categories/{id}")
    suspend fun getCategory(
        @Path("id") id: String
    ): Response<CategoryDTO>
}