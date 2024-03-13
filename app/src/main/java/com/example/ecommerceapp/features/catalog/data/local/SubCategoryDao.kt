package com.example.ecommerceapp.features.catalog.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.features.catalog.data.entity.SubCategoryEntity

@Dao
interface SubCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubCategories(subCategories: List<SubCategoryEntity>)

    @Query("SELECT * FROM ${Constants.SUB_CATEGORY_TABLE_NAME} WHERE categoryID = :categoryID")
    suspend fun getSubCategoriesByCategoryId(categoryID: String): List<SubCategoryEntity>
}