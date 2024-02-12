package com.example.ecommerceapp.features.auth.data.repository

import com.example.ecommerceapp.features.auth.data.Mapper.toUser
import com.example.ecommerceapp.features.auth.data.local.UserDatabase
import com.example.ecommerceapp.features.auth.data.remote.UserApi
import com.example.ecommerceapp.features.auth.data.remote.dto.LoginBodyRequest
import com.example.ecommerceapp.features.auth.data.remote.dto.RegisterRequestBody
import com.example.ecommerceapp.features.auth.domain.model.User
import com.example.ecommerceapp.features.auth.domain.repository.UserRepository
import com.example.ecommerceapp.util.Resource
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: UserApi,
    private val db: UserDatabase
) : UserRepository {

    private val dao = db.dao
    override suspend fun createUser(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        userRole: String
    ): Resource<User> {
        val requestBody = RegisterRequestBody(email, password, firstName, lastName, userRole)
        val response = performRequest { api.createUser(requestBody) }
        if (response.isSuccessful) {
            val user = response.body()!!.toUser()
            dao.insertUser(user)
            return Resource.Success(user.toUser())
        }
        return Resource.Error(response.message())
    }


    override suspend fun loginUser(email: String, password: String): Resource<Nothing> {
        val requestBody = LoginBodyRequest(email, password)
        val response = performRequest { api.loginUser(requestBody) }
        return if (response.isSuccessful) {
            Resource.Success(null)
        } else {
            Resource.Error(response.message())
        }
    }

    private inline fun <T> performRequest(request: () -> Response<T>): Response<T> {
        return try {
            request()
        } catch (e: Exception) {
            Response.error(500,
                "An unexpected error occurred: ${e.localizedMessage}".toResponseBody(null)
            )
        }
    }

}