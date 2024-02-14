package com.example.ecommerceapp.features.auth.domain.usecase

import android.content.Context
import com.example.ecommerceapp.features.auth.data.local.TokenStorage
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val context: Context
) {
    operator fun invoke(accessToken: String, refreshToken: String) {
        TokenStorage.saveTokens(context, accessToken, refreshToken)
    }
}