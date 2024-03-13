package com.example.ecommerceapp.features.catalog.domain.usecase

import com.example.ecommerceapp.features.catalog.domain.models.SubCategory
import com.example.ecommerceapp.features.catalog.domain.repository.SubCategoryRepository
import com.example.ecommerceapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSubCategoriesByCategoryUseCase @Inject constructor(
     private val repository: SubCategoryRepository
){
    suspend operator fun invoke(categoryId: String): Flow<Resource<List<SubCategory>>>{
        return flow {
            when(val subCategories = repository.getSubCategoriesByCategoryId(categoryId)){
                is Resource.Success -> {
                    emit(Resource.Success(subCategories.data))
                }
                is Resource.Error -> {
                    emit(Resource.Error(subCategories.message!!))
                }
                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
            }
        }
    }
}