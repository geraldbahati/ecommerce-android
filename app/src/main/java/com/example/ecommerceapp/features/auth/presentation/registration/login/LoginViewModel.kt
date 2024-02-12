package com.example.ecommerceapp.features.auth.presentation.registration.login

import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.features.auth.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {

}