package com.example.ecommerceapp.features.catalog.di

import com.example.ecommerceapp.features.catalog.data.repository.CategoryRepositoryImpl
import com.example.ecommerceapp.features.catalog.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

}