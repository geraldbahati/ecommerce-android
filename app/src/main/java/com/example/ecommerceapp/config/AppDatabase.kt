package com.example.ecommerceapp.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ecommerceapp.features.auth.data.entity.UserEntity
import com.example.ecommerceapp.features.auth.data.local.UserDao
import com.example.ecommerceapp.features.catalog.data.entity.CategoryEntity
import com.example.ecommerceapp.features.catalog.data.local.CategoryDao

@Database(
    entities = [
        UserEntity::class,
        CategoryEntity::class
   ],
    version = 2
)
abstract class AppDatabase : RoomDatabase(){
    abstract val userDao: UserDao
    abstract val categoryDao: CategoryDao
}