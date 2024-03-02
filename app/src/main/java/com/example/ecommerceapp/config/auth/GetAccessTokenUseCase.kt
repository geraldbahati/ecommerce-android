package com.example.ecommerceapp.config.auth

import android.content.Context
import com.example.ecommerceapp.config.auth.TokenStorage
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
   private val context: Context
){
    operator fun invoke(): String? {
        return TokenStorage.getAccessToken(context)
    }
}