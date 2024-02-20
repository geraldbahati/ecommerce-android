package com.example.ecommerceapp.features.catalog.domain.usecase

import com.example.ecommerceapp.features.catalog.domain.models.Product
import com.example.ecommerceapp.features.catalog.domain.repository.ProductRepository
import com.example.ecommerceapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(query: String): Flow<Resource<List<Product>>> {
        return flow {
            when (val products = repository.getProducts()) {
                is Resource.Success -> {
                    emit(Resource.Success(products.data))
                }
                is Resource.Error -> {
                    emit(Resource.Error(products.message!!))
                }
                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
            }
        }
    }
}