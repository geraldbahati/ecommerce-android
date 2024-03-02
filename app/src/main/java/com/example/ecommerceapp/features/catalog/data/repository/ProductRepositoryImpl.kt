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
    override suspend fun getProducts(): Resource<List<Product>> {
        val storageProducts = dao.getProducts()
        return if (storageProducts.isNotEmpty()) {
            Resource.Success(storageProducts.toProductList())
        } else {
            val response = performRequest { api.getProducts() }
            if (response.isSuccessful) {
                val products = response.body()!!.toProductEntityList()
                dao.insertProducts(products)
                Resource.Success(products.toProductList())
            } else {
                Resource.Error(response.message())
            }
        }
    }

    override suspend fun getProductById(id: String): Resource<Product> {
        val storageProduct = dao.getProduct(id)
        return if (storageProduct != null) {
            Resource.Success(storageProduct.toProduct())
        } else {
            Resource.Error("Product not found")
        }
    }

    override suspend fun searchProduct(query: String): Resource<List<Product>> {
        val storageProducts = dao.searchProducts(query)
        return if (storageProducts.isNotEmpty()) {
            Resource.Success(storageProducts.toProductList())
        } else {
            Resource.Error("Product not found")
        }
    }

    override suspend fun refreshProducts(): Resource<List<Product>> {
        val response = performRequest { api.getProducts() }
        return if (response.isSuccessful) {
            val products = response.body()!!.toProductEntityList()
            dao.clearProducts()
            dao.insertProducts(products)
            Resource.Success(products.toProductList())
        } else {
            Resource.Error(response.message())
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