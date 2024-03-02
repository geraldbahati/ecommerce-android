package com.example.ecommerceapp.features.catalog.data.repository

import com.example.ecommerceapp.database.AppDatabase
import com.example.ecommerceapp.features.catalog.data.entity.toCategoryList
import com.example.ecommerceapp.features.catalog.data.remote.CategoryApi
import com.example.ecommerceapp.features.catalog.data.remote.dto.toCategoryEntityList
import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.example.ecommerceapp.features.catalog.domain.repository.CategoryRepository
import com.example.ecommerceapp.util.Resource
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val api: CategoryApi,
    db: AppDatabase,
) : CategoryRepository {
    private val dao = db.categoryDao
    override suspend fun getCategories(): Resource<List<Category>> {
        val storageCategories = dao.getCategories()
        return if (storageCategories.isNotEmpty()) {
            Resource.Success(storageCategories.toCategoryList())
        } else {
            val response = performRequest { api.getCategories() }
            if (response.isSuccessful) {
                val categories = response.body()!!.toCategoryEntityList()
                dao.insertCategories(categories)
                Resource.Success(categories.toCategoryList())
            } else {
                Resource.Error(response.message())
            }
        }
    }

    override suspend fun getCategoryById(id: String): Resource<Category> {
        val storageCategory = dao.getCategory(id)
        return if (storageCategory != null) {
            Resource.Success(storageCategory.toCategory())
        } else {
           Resource.Error("Category not found")
        }
    }

    override suspend fun searchCategory(query: String): Resource<List<Category>> {
        val storageCategories = dao.searchCategories(query)
        return if (storageCategories.isNotEmpty()) {
            Resource.Success(storageCategories.toCategoryList())
        } else {
            Resource.Error("Category not found")
        }
    }

    override suspend fun refreshCategories(): Resource<List<Category>> {
        val response = performRequest { api.getCategories() }
        return if (response.isSuccessful) {
            val categories = response.body()!!.toCategoryEntityList()
            dao.clearCategories()
            dao.insertCategories(categories)
            Resource.Success(categories.toCategoryList())
        } else {
            Resource.Error(response.message())
        }
    }

    private inline fun <T> performRequest(request: () -> Response<T>): Response<T> {
        return try {
            request()
        } catch (e: Exception) {
            Response.error(500,
                "An unexpected error occurred: ${e.localizedMessage}".toResponseBody(null)
            )
        }
    }
}