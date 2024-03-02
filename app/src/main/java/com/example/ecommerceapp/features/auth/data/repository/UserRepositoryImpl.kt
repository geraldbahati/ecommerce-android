package com.example.ecommerceapp.features.auth.data.repository

import com.example.ecommerceapp.database.AppDatabase
import com.example.ecommerceapp.features.auth.data.Mapper.toUser
import com.example.ecommerceapp.features.auth.data.remote.UserApi
import com.example.ecommerceapp.features.auth.data.remote.dto.LoginBodyRequest
import com.example.ecommerceapp.features.auth.data.remote.dto.RegisterRequestBody
import com.example.ecommerceapp.features.auth.data.remote.dto.ResetPasswordBody
import com.example.ecommerceapp.features.auth.domain.model.ResponseMessage
import com.example.ecommerceapp.features.auth.domain.model.Tokens
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
    db: AppDatabase,
) : UserRepository {

    private val dao = db.userDao
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
            dao.clearUser()
            dao.insertUser(user)
            return Resource.Success(user.toUser())
        }
        return Resource.Error(response.message())
    }


    override suspend fun loginUser(email: String, password: String): Resource<Tokens> {
        val requestBody = LoginBodyRequest(email, password)
        val response = performRequest { api.loginUser(requestBody) }
        return if (response.isSuccessful) {
            val tokens = response.body()!!
            Resource.Success(Tokens(tokens.accessToken, tokens.refreshToken))
        } else {
            Resource.Error(response.message())
        }
    }

    override suspend fun resetPassword(accessToken: String,email: String): Resource<ResponseMessage> {
        val requestBody = ResetPasswordBody(email)
        val response = performRequest { api.resetPassword(accessToken,requestBody) }
        return if (response.isSuccessful) {
            val message = response.body()!!
            Resource.Success(ResponseMessage(message.message))
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