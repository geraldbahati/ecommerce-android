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

    @Query("SELECT * FROM ${Constants.PRODUCT_TABLE_NAME}")
    suspend fun getProducts(): List<ProductEntity>

    @Query(
        """
            SELECT p.* 
            FROM ${Constants.PRODUCT_TABLE_NAME} AS p
            JOIN ${Constants.PRODUCT_TABLE_NAME_FTS} AS fts ON p.rowid = fts.rowid
            WHERE products_fts MATCH :query
        """
    )
    suspend fun searchProducts(query: String): List<ProductEntity>

    @Query("SELECT * FROM ${Constants.PRODUCT_TABLE_NAME} WHERE id = :id")
    suspend fun getProduct(id: String): ProductEntity?

    @Query("DELETE FROM ${Constants.PRODUCT_TABLE_NAME}")
    suspend fun clearProducts()
}