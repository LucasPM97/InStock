package com.lucas.instock.data.local.database.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.instock.data.model.ProductPageInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductPageDao {

    @Query("SELECT * FROM product")
    fun getAllProductsFlow(): Flow<List<ProductPageInfo>>

    @Query("SELECT * FROM product")
    fun getAllProducts(): List<ProductPageInfo>

    @Query("SELECT * FROM product WHERE synced = 1")
    fun getUnsyncedProducts(): List<ProductPageInfo>

    @Query("SELECT * FROM product WHERE productId = :productId LIMIT 1")
    fun getProductById(productId: Int): ProductPageInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductPageInfo)

    @Query("DELETE FROM product WHERE productId = :productId")
    suspend fun deleteProduct(productId: Int)

    @Query("DELETE FROM product")
    suspend fun deleteAll()
}