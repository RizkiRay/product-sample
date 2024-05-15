package com.example.kedatechapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kedatechapp.data.db.dao.ProductDao
import com.example.kedatechapp.data.db.entity.Product

@Database(
    entities = [Product::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun ProductDao(): ProductDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "product.db").build()
    }
}