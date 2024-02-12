package com.example.ecommerceapp.features.auth.presentation.registration.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.features.auth.domain.repository.UserRepository
import com.example.ecommerceapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel(){
    var state by mutableStateOf(RegisterState())

    private var registerJob: Job? = null

    fun onEvent(event: RegisterEvent){
        when(event){
            is RegisterEvent.OnEmailChange -> {
                state = state.copy(
                    email = event.email,
                    emailErrorMessage = null
                )
            }

            is RegisterEvent.OnPasswordChange -> {
                state = state.copy(
                    password = event.password,
                    passwordErrorMessage = null
                )
            }

            is RegisterEvent.OnFirstNameChange -> {
                state = state.copy(
                    firstName = event.firstName,
                    firstNameErrorMessage = null
                )
            }

            is RegisterEvent.OnLastNameChange -> {
                state = state.copy(
                    lastName = event.lastName,
                    lastNameErrorMessage = null
                )
            }

            is RegisterEvent.OnUserRoleChange -> {
                state = state.copy(userRole = event.userRole)
            }

            is RegisterEvent.OnPasswordVisibilityChange -> {
                state = state.copy(isPasswordVisible = !state.isPasswordVisible)
            }
            is RegisterEvent.OnRegister -> {
                if(validator()){
                    register()
                }
            }
            is RegisterEvent.OnNavigateBack ->{
                state = state.copy(isRegister = false)
            }
            is RegisterEvent.OnTermsAndConditionsChange -> {
                state = state.copy(isTermsAndConditionsAccepted = event.isTermsAndConditionsChecked)
            }
            is RegisterEvent.OnError -> {
                state = state.copy(snackbarErrorMessage = event.error)
            }
        }
    }

    private fun register(){
        registerJob?.cancel()
        registerJob = viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.createUser(
                email=  state.email.trim(),
                password= state.password.trim(),
                firstName= state.firstName.trim(),
                lastName =   state.lastName.trim(),
                userRole =  state.userRole.trim()
            )
            state = state.copy(isLoading = false)
            when(result){
                is Resource.Success -> {
                    state = state.copy(isRegister = true)
                }
                is Resource.Error -> {
                    state = state.copy(snackbarErrorMessage = result.message ?: "An unexpected error occurred")
                }
                else -> Unit
            }
        }
    }

    private fun validator(): Boolean{
        var emailError: String? = null
        var passwordError: String? = null
        var firstNameError: String? = null
        var lastNameError: String? = null
        var termsAndConditionsError: String? = null

        // Check if the user has accepted the terms and conditions
        if (!state.isTermsAndConditionsAccepted) {
            termsAndConditionsError = "You must accept the terms and conditions to continue"
        }

        // Validate email
        if (state.email.isBlank()) {
            emailError = "Email is required"
        }

        // Validate password
        if (state.password.length < 6) {
            passwordError = "Password must be at least 6 characters long"
        }

        // Validate first name
        if (state.firstName.isBlank()) {
            firstNameError = "First name is required"
        }

        // Validate last name
        if (state.lastName.isBlank()) {
            lastNameError = "Last name is required"
        }

        // Update state with all error messages
        state = state.copy(
            emailErrorMessage = emailError,
            passwordErrorMessage = passwordError,
            firstNameErrorMessage = firstNameError,
            lastNameErrorMessage = lastNameError,
            snackbarErrorMessage = termsAndConditionsError
        )

        // Check if there are any errors
        return listOf(emailError, passwordError, firstNameError, lastNameError, termsAndConditionsError).all { it == null }
    }

    override fun onCleared() {
        super.onCleared()
        registerJob?.cancel()
    }

}