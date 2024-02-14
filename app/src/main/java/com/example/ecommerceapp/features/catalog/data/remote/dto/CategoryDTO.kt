package com.example.ecommerceapp.features.catalog.data.remote.dto

import com.example.ecommerceapp.features.catalog.data.entity.CategoryEntity
import com.squareup.moshi.Json

data class CategoryDTO (
    @field: Json(name = "id") val id: String,
    @field: Json(name = "name") val name: String,
    @field: Json(name = "description") val description: Description,
    @field: Json(name = "imageURL") val imageURL: Description,
    @field: Json(name = "seoKeywords") val seoKeywords: Description,
    @field: Json(name = "isActive") val isActive: Boolean,
    @field: Json(name = "createdAt") val createdAt: String,
    @field: Json(name = "lastUpdated") val lastUpdated: LastUpdated
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

data class Description (
    @field:Json(name = "String")val string: String,
    @field:Json(name = "Valid")val valid: Boolean
)

data class LastUpdated (
    @field:Json(name = "Time")val time: String,
    @field:Json(name = "Valid")val valid: Boolean
)
