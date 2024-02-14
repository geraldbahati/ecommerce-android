package com.example.ecommerceapp.features.catalog.data.remote.dto

import com.example.ecommerceapp.features.catalog.data.entity.CategoryEntity
import com.squareup.moshi.Json


data class GetCategoriesRequest(
    @field:Json(name = "data") val categories: List<CategoryDTO>,
)
data class CategoryDTO (
    @field: Json(name = "id") val id: String,
    @field: Json(name = "name") val name: String,
    @field: Json(name = "description") val description: Description,
    @field: Json(name = "image_url") val imageURL: Description,
    @field: Json(name = "seo_keywords") val seoKeywords: Description,
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

data class Description (
    @field:Json(name = "String")val string: String,
    @field:Json(name = "Valid")val valid: Boolean
)

data class LastUpdated (
    @field:Json(name = "Time")val time: String,
    @field:Json(name = "Valid")val valid: Boolean
)


fun GetCategoriesRequest.toCategoryEntityList() = categories.map { it.toCategoryEntity() }