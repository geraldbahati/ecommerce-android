package com.example.ecommerceapp.features.auth.domain.usecase

import com.example.ecommerceapp.features.auth.domain.repository.UserRepository
import com.example.ecommerceapp.util.Resource
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        userRole: String
    ):Resource<Unit> {
        return when (val registerResponse = userRepository.createUser(email, password, firstName, lastName, userRole)) {
            is Resource.Success -> {
                Resource.Success(Unit)
            }

            is Resource.Error -> {
                Resource.Error(registerResponse.message!!)
            }

            is Resource.Loading -> {
                Resource.Loading()
            }
        }
    }
}