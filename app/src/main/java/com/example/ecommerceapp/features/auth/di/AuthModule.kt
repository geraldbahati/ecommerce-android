package com.example.ecommerceapp.features.auth.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.features.auth.data.local.UserDatabase
import com.example.ecommerceapp.features.auth.data.remote.UserApi
import com.example.ecommerceapp.features.auth.domain.usecase.GetAccessTokenUseCase
import com.example.ecommerceapp.features.auth.domain.usecase.SaveTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }).build())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideSaveTokenUseCase(
        @ApplicationContext context: Context
    ): SaveTokenUseCase {
        return SaveTokenUseCase(context)
    }

    @Provides
    @Singleton
    fun provideGetAccessTokenUseCase(
        @ApplicationContext context: Context
    ): GetAccessTokenUseCase {
        return GetAccessTokenUseCase(context)
    }
}