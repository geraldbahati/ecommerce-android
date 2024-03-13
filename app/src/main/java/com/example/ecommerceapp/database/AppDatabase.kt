package com.example.ecommerceapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.features.auth.data.entity.UserEntity
import com.example.ecommerceapp.features.auth.data.local.UserDao
import com.example.ecommerceapp.features.catalog.data.entity.CategoryEntity
import com.example.ecommerceapp.features.catalog.data.entity.CategoryFtsEntity
import com.example.ecommerceapp.features.catalog.data.entity.ProductEntity
import com.example.ecommerceapp.features.catalog.data.entity.ProductFtsEntity
import com.example.ecommerceapp.features.catalog.data.entity.SubCategoryEntity
import com.example.ecommerceapp.features.catalog.data.local.CategoryDao
import com.example.ecommerceapp.features.catalog.data.local.ProductDao
import com.example.ecommerceapp.features.catalog.data.local.SubCategoryDao

@Database(
    entities = [
        UserEntity::class,
        CategoryEntity::class,
        ProductEntity::class,
        ProductFtsEntity::class,
        CategoryFtsEntity::class,
        SubCategoryEntity::class
   ],
    version = Constants.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase(){
    abstract val userDao: UserDao
    abstract val categoryDao: CategoryDao
    abstract val productDao: ProductDao
    abstract val subCategoryDao: SubCategoryDao
}