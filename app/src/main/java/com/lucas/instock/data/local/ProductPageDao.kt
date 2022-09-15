package com.lucas.instock.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.instock.data.model.ProductPageInfo

@Dao
interface ProductPageDao {

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): List<ProductPageInfo>

    @Query("SELECT * FROM product_table WHERE synced = 1")
    fun getUnsyncedProducts(): List<ProductPageInfo>

    @Query("SELECT * FROM product_table WHERE productId = :productId")
    fun getProductById(productId: Int): ProductPageInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductPageInfo)

    @Query("DELETE FROM product_table WHERE productId = :productId")
    suspend fun deleteProduct(productId: Int)

    @Query("DELETE FROM product_table")
    suspend fun deleteAll()
}