package com.example.ecommerceapp.features.catalog.data.remote.dto

import com.example.ecommerceapp.features.catalog.data.entity.CategoryEntity
import com.squareup.moshi.Json


data class GetCategoriesRequest(
    @field:Json(name = "data") val categories: List<CategoryDTO>,
)
data class CategoryDTO (
    @field: Json(name = "id") val id: String,
    @field: Json(name = "name") val name: String,
    @field: Json(name = "description") val description: NullSQLData,
    @field: Json(name = "image_url") val imageURL: NullSQLData,
    @field: Json(name = "seo_keywords") val seoKeywords: NullSQLData,
    @field: Json(name = "is_active") val isActive: Boolean,
) {
    fun toCategoryEntity() = CategoryEntity(
        id = id,
        name = name,
        description = description.string,
        imageURL = imageURL.string,
        seoKeywords = seoKeywords.string,
        isActive = isActive
    )
}

fun GetCategoriesRequest.toCategoryEntityList() = categories.map { it.toCategoryEntity() }