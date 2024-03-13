package com.example.ecommerceapp.features.catalog.data.remote

import com.example.ecommerceapp.features.catalog.data.remote.dto.PaginatedSubCategoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SubCategoryApi {

    @GET("sub-categories/{categoryId}")
    suspend fun getSubCategoriesByCategoryId(
        @Path("categoryId") categoryId: String
    ): Response<PaginatedSubCategoryResponse>

}