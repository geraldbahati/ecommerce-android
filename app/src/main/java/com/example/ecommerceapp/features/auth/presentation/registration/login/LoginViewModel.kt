package com.example.ecommerceapp.features.auth.presentation.registration.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.config.Routes
import com.example.ecommerceapp.features.auth.domain.repository.UserRepository
import com.example.ecommerceapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    var state by mutableStateOf(LoginState())

    private var loginJob: Job? = null

    fun onEvent(event: LoginEvent){
        when(event) {
            is LoginEvent.OnSetNavigator -> {
                state = state.copy(navigator = event.navigator)
            }

            is LoginEvent.OnEmailChange -> {
                state = state.copy(
                    email = event.email,
                    emailErrorMessage = null
                )
            }

            is LoginEvent.OnPasswordChange -> {
                state = state.copy(
                    password = event.password,
                    passwordErrorMessage = null
                )
            }


            is LoginEvent.OnPasswordVisibilityChange -> {
                state = state.copy(isPasswordVisible = !state.isPasswordVisible)
            }

            is LoginEvent.OnNavigateToRegister -> {
                state.navigator?.navigate(Routes.REGISTER)
            }

            is LoginEvent.OnNavigateToForgotPassword -> {
//                state.navigator?.navigate(Routes.FORGOT_PASSWORD)
            }

            is LoginEvent.OnNavigateToHome -> {
//                state.navigator?.navigate(Routes.HOME)
            }

            is LoginEvent.OnLogin -> {
                if (validated()) {
                    login()
                }
            }
        }
    }

    private fun validated(): Boolean {
        var emailError: String? = null
        var passwordError: String? = null

        if (state.email.isBlank()) {
            emailError = "Email is required"
        }

        if (state.password.isBlank()) {
            passwordError = "Password is required"
        }

        state = state.copy(
            emailErrorMessage = emailError,
            passwordErrorMessage = passwordError
        )

        return listOf(emailError, passwordError).all { it.isNullOrBlank() }
    }

    private fun login() {
        loginJob?.cancel()
        loginJob = viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = userRepository.loginUser(state.email.trim(), state.password.trim())
            state = state.copy(isLoading = false)
            state = when (result) {
                is Resource.Success -> {
                    state.copy(isLogin = true)
                }

                is Resource.Error -> {
                    state.copy(snackbarErrorMessage = result.message!!)
                }

                is Resource.Loading -> {
                    state.copy(isLoading = true)
                }
            }
        }
    }
}