package com.example.ecommerceapp.features.auth.presentation.registration.forget_password



data class ForgetPasswordState(
    val isLoading: Boolean = false,
    val isPasswordReset: Boolean = false,
    val email: String = "",
    val message: String = "",
    val emailErrorMessage: String? = null,
    val snackbarErrorMessage: String? = null
)