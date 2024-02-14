package com.example.ecommerceapp.features.catalog.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.features.catalog.domain.models.Category

@Entity(tableName = Constants.CATEGORY_TABLE_NAME)
data class CategoryEntity (
    @PrimaryKey val id: String,
    val name: String,
    val description: String?,
    val imageURL: String?,
    val seoKeywords: String?,
    val isActive: Boolean,
){
    fun toCategory() = Category(
        id = id,
        name = name,
        description = description,
        imageURL = imageURL,
        seoKeywords = seoKeywords,
        isActive = isActive
    )
}

fun List<CategoryEntity>.toCategoryList() = map { it.toCategory() }