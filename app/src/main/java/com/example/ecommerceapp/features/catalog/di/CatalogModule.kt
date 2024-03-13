package com.example.ecommerceapp.features.catalog.di

import com.example.ecommerceapp.database.AppDatabase
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.features.catalog.data.local.CategoryDao
import com.example.ecommerceapp.features.catalog.data.local.ProductDao
import com.example.ecommerceapp.features.catalog.data.local.SubCategoryDao
import com.example.ecommerceapp.features.catalog.data.remote.CategoryApi
import com.example.ecommerceapp.features.catalog.data.remote.ProductApi
import com.example.ecommerceapp.features.catalog.data.remote.SubCategoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatalogModule {

    @Provides
    @Singleton
    fun provideCategoryApi(): CategoryApi {
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
    fun provideProductApi(): ProductApi {
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
    fun provideSubCategoryApi(): SubCategoryApi {
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
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao {
        return appDatabase.categoryDao
    }

    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao
    }

    @Provides
    fun provideSubCategoryDao(appDatabase: AppDatabase): SubCategoryDao {
        return appDatabase.subCategoryDao
    }
}