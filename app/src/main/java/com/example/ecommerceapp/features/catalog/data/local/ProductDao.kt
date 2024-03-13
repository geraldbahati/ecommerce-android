package com.example.ecommerceapp.features.catalog.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.features.catalog.data.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(productEntities: List<ProductEntity>)

    @Query("SELECT * FROM ${Constants.PRODUCT_TABLE_NAME} WHERE subCategoryId = :subCategoryId")
    suspend fun getProductsBySubCategory(subCategoryId: String): List<ProductEntity>

}