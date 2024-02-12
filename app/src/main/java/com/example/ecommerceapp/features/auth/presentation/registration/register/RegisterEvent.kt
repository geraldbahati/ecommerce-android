package com.example.ecommerceapp.features.auth.presentation.registration.register

sealed class RegisterEvent {
    data class OnEmailChange(val email: String): RegisterEvent()
    data class OnPasswordChange(val password: String): RegisterEvent()
    data class OnFirstNameChange(val firstName: String): RegisterEvent()
    data class OnLastNameChange(val lastName: String): RegisterEvent()
    data class OnUserRoleChange(val userRole: String): RegisterEvent()
//    data class OnPasswordVisibilityChange(val isPasswordVisible: Boolean): RegisterEvent()
    data class OnTermsAndConditionsChange(val isTermsAndConditionsChecked: Boolean): RegisterEvent()
    data class OnError(val error: String): RegisterEvent()
    data object OnRegister: RegisterEvent()
    data object OnNavigateBack: RegisterEvent()
    data object OnPasswordVisibilityChange: RegisterEvent()
}