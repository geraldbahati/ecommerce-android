package com.example.ecommerceapp.features.catalog.di

import com.example.ecommerceapp.features.catalog.data.repository.CategoryRepositoryImpl
import com.example.ecommerceapp.features.catalog.data.repository.ProductRepositoryImpl
import com.example.ecommerceapp.features.catalog.data.repository.SubCategoryRepositoryImpl
import com.example.ecommerceapp.features.catalog.domain.repository.CategoryRepository
import com.example.ecommerceapp.features.catalog.domain.repository.ProductRepository
import com.example.ecommerceapp.features.catalog.domain.repository.SubCategoryRepository
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

    @Binds
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    abstract fun bindSubCategoryRepository(
        subCategoryRepositoryImpl: SubCategoryRepositoryImpl
    ): SubCategoryRepository
}