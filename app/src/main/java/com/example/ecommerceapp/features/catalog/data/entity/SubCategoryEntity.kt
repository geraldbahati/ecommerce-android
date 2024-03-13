package com.example.ecommerceapp.features.catalog.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.features.catalog.domain.models.SubCategory

@Entity(
    tableName = Constants.SUB_CATEGORY_TABLE_NAME,
    foreignKeys =  [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SubCategoryEntity (
    @PrimaryKey val id: String,
    val categoryID: String,
    val name: String,
    val description: String?,
    val imageURL: String?,
    val seoKeywords: String?,
    val isActive: Boolean,
    ) {
    fun toSubCategory() = SubCategory(
        id = id,
        categoryID = categoryID,
        name = name,
        description= description,
        imageURL = imageURL,
        seoKeywords = seoKeywords,
        isActive = isActive
    )
}

fun List<SubCategoryEntity>.toSubCategoryList() = map { it.toSubCategory() }