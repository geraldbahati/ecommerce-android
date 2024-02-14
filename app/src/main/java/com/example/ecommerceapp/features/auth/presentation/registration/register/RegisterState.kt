package com.example.ecommerceapp.features.auth.presentation.registration.register

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

data class RegisterState(
    val isLoading: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isRegister: Boolean = false,
    val isTermsAndConditionsAccepted: Boolean = false,
    val navigator: DestinationsNavigator? = null,

    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val userRole: String = "customer",

    // error message
    val emailErrorMessage: String? = null,
    val passwordErrorMessage: String? = null,
    val firstNameErrorMessage: String? = null,
    val lastNameErrorMessage: String? = null,

    // snackbar message
    val snackbarErrorMessage: String? = null
)