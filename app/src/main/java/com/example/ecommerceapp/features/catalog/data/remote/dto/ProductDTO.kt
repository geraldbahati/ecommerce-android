package com.example.ecommerceapp.features.catalog.data.remote.dto

import com.example.ecommerceapp.features.catalog.data.entity.ProductEntity
import com.squareup.moshi.Json


data class PaginatedProductsResponse(
    @field: Json(name = "data") val data: List<ProductDTO>
)
data class ProductDTO (
    @field: Json(name = "id") val id: String,
    @field: Json(name = "name") val name: String,
    @field: Json(name = "description") val description: NullSQLData,
    @field: Json(name = "image_url") val imageURL: NullSQLData,
    @field: Json(name = "price") val price: Double,
    @field: Json(name = "stock") val stock: Int,
    @field: Json(name= "sub_category_id")val subCategoryId: String,
    @field: Json(name= "rating")val rating: Double,
    @field: Json(name= "review_count")val reviewCount: Int,
    @field: Json(name= "discount_rate")val discountRate: String,
    @field: Json(name= "keywords")val keywords: NullSQLData
){
    fun toProductEntity() = ProductEntity(
        id = id,
        name = name,
        description = description.string,
        imageURL = imageURL.string,
        price = price,
        stock = stock,
        subCategoryId = subCategoryId,
        rating = rating,
        reviewCount = reviewCount,
        discountRate = discountRate,
        keywords = keywords.string
    )
}

fun PaginatedProductsResponse.toProductEntityList() = data.map { it.toProductEntity() }