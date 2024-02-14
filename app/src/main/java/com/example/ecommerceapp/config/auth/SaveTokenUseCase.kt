package com.example.ecommerceapp.config.auth

import android.content.Context
import com.example.ecommerceapp.config.auth.TokenStorage
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val context: Context
) {
    operator fun invoke(accessToken: String, refreshToken: String) {
        TokenStorage.saveTokens(context, accessToken, refreshToken)
    }
}