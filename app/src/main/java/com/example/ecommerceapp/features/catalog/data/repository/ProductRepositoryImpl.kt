package com.example.ecommerceapp.features.catalog.data.repository

import com.example.ecommerceapp.database.AppDatabase
import com.example.ecommerceapp.features.catalog.data.entity.toProductList
import com.example.ecommerceapp.features.catalog.data.remote.ProductApi
import com.example.ecommerceapp.features.catalog.data.remote.dto.toProductEntityList
import com.example.ecommerceapp.features.catalog.domain.models.Product
import com.example.ecommerceapp.features.catalog.domain.repository.ProductRepository
import com.example.ecommerceapp.util.Resource
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi,
    db: AppDatabase,
) : ProductRepository {
    private val dao = db.productDao


    override suspend fun getProductsBySubCategory(subCategoryId: String): Resource<List<Product>> {
        val cachedProducts = dao.getProductsBySubCategory(subCategoryId)
        if (cachedProducts.isNotEmpty()) {
            return Resource.Success(cachedProducts.toProductList())
        }

        val response = performRequest { api.getProductsBySubCategory(subCategoryId) }
        if (response.isSuccessful) {
            val products = response.body()?.toProductEntityList()
            products?.let { dao.insertProducts(it) }
            return Resource.Success(products?.toProductList() ?: emptyList())
        }

        return Resource.Error(response.message())
    }

    private suspend fun <T> performRequest(request: suspend () -> Response<T>): Response<T> {
        return try {
            request()
        } catch (e: Exception) {
            Response.error(500, "Internal server error".toResponseBody())
        }
    }
}