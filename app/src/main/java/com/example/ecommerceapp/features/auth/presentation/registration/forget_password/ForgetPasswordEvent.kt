package com.example.ecommerceapp.features.auth.presentation.registration.forget_password

sealed class ForgetPasswordEvent {
    data class OnEmailChange(val email: String): ForgetPasswordEvent()
    data object OnResetPassword: ForgetPasswordEvent()
}