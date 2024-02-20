package com.example.ecommerceapp.features.catalog.data.remote.dto

import com.example.ecommerceapp.features.catalog.data.entity.ProductEntity
import com.squareup.moshi.Json

data class ProductDTO (
    @field: Json(name = "id") val id: String,
    @field: Json(name = "name") val name: String,
    @field: Json(name = "description") val description: Brand,
    @field: Json(name = "image_url") val imageURL: Brand,
    @field: Json(name = "price") val price: Double,
    @field: Json(name = "stock") val stock: Int,
    @field: Json(name= "category_id")val categoryID: String,
    @field: Json(name= "rating")val rating: Double,
    @field: Json(name= "review_count")val reviewCount: Int,
    @field: Json(name= "discount_rate")val discountRate: String,
    @field: Json(name= "keywords")val keywords: Brand
){
    fun toProductEntity() = ProductEntity(
        id = id,
        name = name,
        description = description.string,
        imageURL = imageURL.string,
        price = price,
        stock = stock,
        categoryID = categoryID,
        rating = rating,
        reviewCount = reviewCount,
        discountRate = discountRate,
        keywords = keywords.string
    )
}

data class Brand (
    @field: Json(name = "String")val string: String,
    @field: Json(name = "Valid")val valid: Boolean
)

fun List<ProductDTO>.toProductEntityList() = map { it.toProductEntity() }