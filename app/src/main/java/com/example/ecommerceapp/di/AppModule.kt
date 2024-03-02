package com.example.ecommerceapp.di

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.room.Room
import androidx.work.WorkerFactory
import com.example.ecommerceapp.database.AppDatabase
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.config.auth.GetAccessTokenUseCase
import com.example.ecommerceapp.config.auth.SaveTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideWorkFactory(
        workerFactory: HiltWorkerFactory
    ): WorkerFactory = workerFactory

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