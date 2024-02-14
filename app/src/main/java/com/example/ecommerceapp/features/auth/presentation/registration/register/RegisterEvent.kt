package com.example.ecommerceapp.features.auth.presentation.registration.register

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class RegisterEvent {
    data class OnSetNavigator(val navigator: DestinationsNavigator): RegisterEvent()
    data class OnEmailChange(val email: String): RegisterEvent()
    data class OnPasswordChange(val password: String): RegisterEvent()
    data class OnFirstNameChange(val firstName: String): RegisterEvent()
    data class OnLastNameChange(val lastName: String): RegisterEvent()
    data class OnUserRoleChange(val userRole: String): RegisterEvent()
    data class OnTermsAndConditionsChange(val isTermsAndConditionsChecked: Boolean): RegisterEvent()
    data class OnError(val error: String): RegisterEvent()
    data object OnRegister: RegisterEvent()
    data object OnNavigateToLogin: RegisterEvent()
    data object OnNavigateToHome: RegisterEvent()
    data object OnNavigateToTermsAndConditions: RegisterEvent()
    data object OnPasswordVisibilityChange: RegisterEvent()
}