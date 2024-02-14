package com.example.ecommerceapp.features.catalog.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerceapp.config.Constants

@Entity(tableName = Constants.CATEGORY_TABLE_NAME)
data class CategoryEntity (
    @PrimaryKey val id: String,
    val name: String,
    val description: String?,
    val imageURL: String?,
    val seoKeywords: String?,
    val isActive: Boolean,
)