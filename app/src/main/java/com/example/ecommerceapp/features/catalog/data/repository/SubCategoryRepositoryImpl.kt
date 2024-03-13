package com.example.ecommerceapp.features.catalog.data.repository

import android.util.Log
import com.example.ecommerceapp.database.AppDatabase
import com.example.ecommerceapp.features.catalog.data.entity.toSubCategoryList
import com.example.ecommerceapp.features.catalog.data.remote.SubCategoryApi
import com.example.ecommerceapp.features.catalog.data.remote.dto.toSubCategoryEntityList
import com.example.ecommerceapp.features.catalog.domain.models.SubCategory
import com.example.ecommerceapp.features.catalog.domain.repository.SubCategoryRepository
import com.example.ecommerceapp.util.Resource
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubCategoryRepositoryImpl @Inject constructor(
    private val api: SubCategoryApi,
    db: AppDatabase,
) : SubCategoryRepository {
    private val dao = db.subCategoryDao

    override suspend fun getSubCategoriesByCategoryId(categoryId: String): Resource<List<SubCategory>> {
        val storageSubCategories = dao.getSubCategoriesByCategoryId(categoryId)
        return if (storageSubCategories.isNotEmpty()) {
            Resource.Success(storageSubCategories.toSubCategoryList())
        } else {
            val response = performRequest { api.getSubCategoriesByCategoryId(categoryId) }
            if (response.isSuccessful) {
                val subCategories = response.body()!!.toSubCategoryEntityList()
                Log.d("SubCategoryRepository", "getSubCategoriesByCategoryId: ${subCategories[0]}")
                dao.insertSubCategories(subCategories)
                Resource.Success(subCategories.toSubCategoryList())
            } else {
                Resource.Error(response.message())
            }
        }
    }

    private suspend fun <T> performRequest(request: suspend () -> Response<T>): Response<T> {
        return try {
            request()
        } catch (e: Exception) {
            Response.error(500, "Internal server error".toResponseBody())
        }
    }
}