package com.example.ecommerceapp.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE ProductEntity ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0")
    }
}