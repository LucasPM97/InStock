package com.lucas.instock.data.local.database.price

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.instock.data.model.ProductPageInfo
import com.lucas.instock.data.model.ProductPrice
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductPriceDao {

    @Query("SELECT DISTINCT productId, price, id, createdAt FROM price ORDER BY createdAt DESC")
    fun getLatestPricePerProductFlow(): Flow<List<ProductPrice>>

    @Query("SELECT * FROM price WHERE productId = :productId ORDER BY createdAt DESC LIMIT 1")
    fun getLatestProductPrice(productId: Int): ProductPrice

    @Query("SELECT * FROM price WHERE productId = :productId ORDER BY createdAt")
    fun getProductPriceHistory(productId: Int): List<ProductPrice>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(price: ProductPrice)

    @Query("DELETE FROM price WHERE productId = :productId")
    suspend fun deleteProduct(productId: Int)

    @Query("DELETE FROM price")
    suspend fun deleteAll()
}