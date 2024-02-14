package com.example.ecommerceapp.features.auth.domain.usecase

import com.example.ecommerceapp.features.auth.domain.repository.UserRepository
import com.example.ecommerceapp.util.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val saveTokenUseCase: SaveTokenUseCase
) {
    suspend operator fun invoke(email: String, password: String) : Resource<Unit>{
        return when (val loginResponse = userRepository.loginUser(email, password)) {
            is Resource.Success -> {
                loginResponse.data?.let { saveTokenUseCase(it.accessToken, it.refreshToken) }
                Resource.Success(Unit)
            }

            is Resource.Error -> {
                Resource.Error(loginResponse.message!!)
            }

            is Resource.Loading -> {
                Resource.Loading()
            }
        }
    }
}