package com.example.ecommerceapp.features.catalog.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.features.catalog.data.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categoryEntities: List<CategoryEntity>)

    @Query("SELECT * FROM ${Constants.CATEGORY_TABLE_NAME}")
    suspend fun getCategories(): List<CategoryEntity>

    @Query(
        """
            SELECT c.* 
            FROM ${Constants.CATEGORY_TABLE_NAME} AS c
            JOIN ${Constants.CATEGORY_TABLE_NAME_FTS} AS fts ON c.rowid = fts.rowid
            WHERE categories_fts MATCH :query
        """
    )
    suspend fun searchCategories(query: String): List<CategoryEntity>

    @Query("SELECT * FROM ${Constants.CATEGORY_TABLE_NAME} WHERE id = :id")
    suspend fun getCategory(id: String): CategoryEntity?

    @Query("DELETE FROM ${Constants.CATEGORY_TABLE_NAME}")
    suspend fun clearCategories()
}