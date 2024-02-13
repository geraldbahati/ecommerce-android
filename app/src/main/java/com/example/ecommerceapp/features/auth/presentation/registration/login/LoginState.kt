package com.example.ecommerceapp.features.auth.presentation.registration.login

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

data class LoginState(
    val isLoading: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isLogin: Boolean = false,
    val navigator: DestinationsNavigator? = null,

    val email: String = "",
    val password: String = "",

    // error message
    val emailErrorMessage: String? = null,
    val passwordErrorMessage: String? = null,

    // snackbar message
    val snackbarErrorMessage: String? = null
)