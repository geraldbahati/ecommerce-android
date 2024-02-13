package com.example.ecommerceapp.features.auth.presentation.registration.login

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class LoginEvent {
    data class OnSetNavigator(val navigator: DestinationsNavigator): LoginEvent()
    data class OnEmailChange(val email: String): LoginEvent()
    data class OnPasswordChange(val password: String): LoginEvent()

    data object OnLogin: LoginEvent()
    data object OnNavigateToRegister: LoginEvent()
    data object OnNavigateToForgotPassword: LoginEvent()
    data object OnNavigateToHome: LoginEvent()
    data object OnPasswordVisibilityChange: LoginEvent()
}