package com.example.ecommerceapp.features.auth.di

import com.example.ecommerceapp.features.auth.data.repository.UserRepositoryImpl
import com.example.ecommerceapp.features.auth.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

}