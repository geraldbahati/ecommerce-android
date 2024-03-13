package com.example.ecommerceapp.features.catalog.data.remote.dto

import com.example.ecommerceapp.features.catalog.data.entity.SubCategoryEntity
import com.squareup.moshi.Json

data class PaginatedSubCategoryResponse (
    @field: Json(name = "data") val data: List<SubCategoryDTO>
)

data class SubCategoryDTO (
    @field: Json(name = "id") val id: String,
    @field: Json(name = "category_id") val categoryID: String,
    @field: Json(name = "name") val name: String,
    @field: Json(name = "description") val description: NullSQLData,
    @field: Json(name = "image_url") val imageURL: NullSQLData,
    @field: Json(name = "seo_keywords") val seoKeywords: NullSQLData,
    @field: Json(name = "is_active") val isActive: Boolean,
) {
    fun toSubCategoryEntity() = SubCategoryEntity(
        id = id,
        categoryID = categoryID,
        name = name,
        description = description.string,
        imageURL = imageURL.string,
        seoKeywords = seoKeywords.string,
        isActive = isActive
    )
}

fun PaginatedSubCategoryResponse.toSubCategoryEntityList() = data.map { it.toSubCategoryEntity() }