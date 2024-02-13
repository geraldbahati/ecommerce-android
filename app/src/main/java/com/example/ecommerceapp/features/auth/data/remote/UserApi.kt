package com.example.ecommerceapp.features.auth.data.remote

import com.example.ecommerceapp.features.auth.data.remote.dto.LoginBodyRequest
import com.example.ecommerceapp.features.auth.data.remote.dto.LoginResponse
import com.example.ecommerceapp.features.auth.data.remote.dto.MessageResponseDTO
import com.example.ecommerceapp.features.auth.data.remote.dto.RegisterRequestBody
import com.example.ecommerceapp.features.auth.data.remote.dto.UserResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {

    @POST("users/login")
    suspend fun loginUser(
        @Body loginBodyRequest: LoginBodyRequest
    ): Response<LoginResponse>

    @POST("users/register")
    suspend fun createUser(
        @Body registerRequestBody: RegisterRequestBody
    ): Response<UserResponseDTO>

    @POST("users/reset-password")
    suspend fun resetPassword(
        @Header("Authorization") token: String,
        @Body email: String
    ): Response<MessageResponseDTO>
}