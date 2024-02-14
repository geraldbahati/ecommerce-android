package com.example.ecommerceapp.features.auth.domain.usecase

import android.content.Context
import com.example.ecommerceapp.features.auth.data.local.TokenStorage
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
   private val context: Context
){
    operator fun invoke(): String? {
        return TokenStorage.getAccessToken(context)
    }
}