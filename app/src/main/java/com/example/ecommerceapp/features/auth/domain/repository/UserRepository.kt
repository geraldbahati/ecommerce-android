package com.example.ecommerceapp.features.auth.domain.repository

import com.example.ecommerceapp.features.auth.domain.model.ResponseMessage
import com.example.ecommerceapp.features.auth.domain.model.Tokens
import com.example.ecommerceapp.features.auth.domain.model.User
import com.example.ecommerceapp.util.Resource

interface UserRepository {
    suspend fun createUser(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        userRole: String = "customer"
    ): Resource<User>
    suspend fun loginUser(email: String, password: String): Resource<Tokens>

    suspend fun resetPassword(accessToken: String,email: String): Resource<ResponseMessage>
}