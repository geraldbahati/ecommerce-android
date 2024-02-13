package com.example.ecommerceapp.features.auth.presentation.registration.forget_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.features.auth.domain.usecase.ResetPasswordUseCase
import com.example.ecommerceapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
): ViewModel(){
    var state by mutableStateOf(ForgetPasswordState())

    private var resetPasswordJob: Job? = null

    fun onEvent(event: ForgetPasswordEvent){
        when(event){

            is ForgetPasswordEvent.OnEmailChange -> {
                state = state.copy(
                    email = event.email,
                    emailErrorMessage = null
                )
            }

            is ForgetPasswordEvent.OnResetPassword -> {
                if(validated()){
                    resetPassword()
                }
            }
        }
    }

    private fun validated(): Boolean {
        var emailError: String? = null

        if(state.email.isEmpty()){
            emailError = "Email is required"
        }

        state = state.copy(
            emailErrorMessage = emailError
        )

        return emailError == null
    }

    private fun resetPassword(){
        resetPasswordJob?.cancel()
        resetPasswordJob = viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = resetPasswordUseCase(state.email)
            state = state.copy(isLoading = false)

            when(result){
                is Resource.Success -> {
                    state = state.copy(
                        isPasswordReset = true,
                        message = result.data!!.message
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        snackbarErrorMessage = result.message!!
                    )
                }

                is Resource.Loading -> {
                    state = state.copy(
                        isLoading = true
                    )
                }
            }
        }
    }

}