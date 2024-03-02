package com.example.ecommerceapp.features.catalog.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.features.catalog.domain.models.Product

@Entity(tableName = Constants.PRODUCT_TABLE_NAME_FTS)
@Fts4(contentEntity = ProductEntity::class)
data class ProductFtsEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "keywords") val keywords: String
)

@Entity(
    tableName = Constants.PRODUCT_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val imageURL: String,
    val price: Double,
    val stock: Int,
    val categoryID: String,
    val rating: Double,
    val reviewCount: Int,
    val discountRate: String,
    val keywords: String
) {
    fun toProduct() = Product(
        id = id,
        name = name,
        description = description,
        imageURL = imageURL,
        price = price,
        stock = stock,
        categoryID = categoryID,
        rating = rating,
        reviewCount = reviewCount,
        discountRate = discountRate,
        keywords = keywords
    )
}

fun List<ProductEntity>.toProductList() = map { it.toProduct() }