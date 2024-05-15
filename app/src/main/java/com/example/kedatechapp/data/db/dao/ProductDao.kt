package com.example.kedatechapp.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.kedatechapp.data.db.entity.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE name LIKE '%' || :name || '%' AND isFavorite = :isFavorite")
    fun searchProductWithFilter(name: String?, isFavorite: Boolean): List<Product>

    @Query("SELECT * FROM product WHERE name LIKE '%' || :name || '%'")
    fun searchAllProducts(name: String?): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg products: Product): List<Long>

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun delete(product: Product)
}