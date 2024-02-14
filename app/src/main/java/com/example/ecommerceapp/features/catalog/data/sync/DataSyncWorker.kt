package com.example.ecommerceapp.features.catalog.data.sync

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.ecommerceapp.features.catalog.data.local.CategoryDao
import com.example.ecommerceapp.features.catalog.data.remote.CategoryApi
import com.example.ecommerceapp.features.catalog.data.remote.dto.toCategoryEntityList
import com.example.ecommerceapp.features.catalog.domain.repository.CategoryRepository
import com.example.ecommerceapp.util.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class DataSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val categoryRepository: CategoryRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            Log.i("DataSyncWorker", "Syncing data...")
            val response = categoryRepository.refreshCategories()
            if (response is Resource.Success) {
                Log.i("DataSyncWorker", "Data synced successfully")
                Result.success()
            } else {
                Log.i("DataSyncWorker", "Error: ${response.message}")
                Result.retry()
            }
        } catch (e: Exception) {
            Log.i("DataSyncWorker", "Error: ${e.localizedMessage}")
            Result.retry()
        }
    }
}