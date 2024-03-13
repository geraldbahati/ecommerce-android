package com.example.ecommerceapp.config

object Constants {
    const val BASE_URL = "http://192.168.0.104:8000/api/"
//    const val BASE_URL = "http://10.0.2.2:8000/api/"
    const val DATABASE_NAME = "ecommerce_db"
    const val DATABASE_VERSION = 5
    const val SHARED_PREFERENCES_NAME = "ecommerce_shared_pref"
    const val DATA_SYNC_WORKER_NAME ="DataSyncWork"

    const val SPLASH_SCREEN_DURATION = 3000L

    const val USER_TABLE_NAME = "users"
    const val CATEGORY_TABLE_NAME = "categories"
    const val PRODUCT_TABLE_NAME = "products"
    const val PRODUCT_TABLE_NAME_FTS = "products_fts"
    const val CATEGORY_TABLE_NAME_FTS = "categories_fts"
    const val SUB_CATEGORY_TABLE_NAME = "sub_categories"

}