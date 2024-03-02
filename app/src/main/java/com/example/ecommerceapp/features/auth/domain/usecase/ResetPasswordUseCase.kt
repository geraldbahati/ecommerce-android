package com.example.ecommerceapp.features.auth.domain.usecase

import android.util.Log
import com.example.ecommerceapp.config.auth.GetAccessTokenUseCase
import com.example.ecommerceapp.features.auth.domain.model.ResponseMessage
import com.example.ecommerceapp.features.auth.domain.repository.UserRepository
import com.example.ecommerceapp.util.Resource
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
){
    suspend operator fun invoke(email: String): Resource<ResponseMessage> {
        val accessToken = getAccessTokenUseCase()
        val token = "Bearer ${accessToken ?: ""}"
        return when (val response = userRepository.resetPassword(accessToken = token, email = email)) {
            is Resource.Success -> {
                response
            }

            is Resource.Error -> {
                Resource.Error(response.message!!)
            }

            is Resource.Loading -> {
                Resource.Loading()
            }
        }
    }
}