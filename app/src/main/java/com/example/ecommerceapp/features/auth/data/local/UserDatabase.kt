package com.example.ecommerceapp.features.auth.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ecommerceapp.features.auth.data.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase(){
    abstract val dao: UserDao
}
