package com.lucas.instock.data.local

import androidx.room.Dao
import com.lucas.instock.data.model.ProductPageInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface IProductLocalDataSource {
    suspend fun storeProductChanges(productPageInfo: ProductPageInfo)
    suspend fun getAllUnsyncedProducts(): List<ProductPageInfo>
    suspend fun getAllProducts(): List<ProductPageInfo>
}

class ProductLocalDataSource(
    private val productDao: ProductPageDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IProductLocalDataSource {
    override suspend fun storeProductChanges(productPageInfo: ProductPageInfo) {
        return withContext(dispatcher) {
            productDao.insert(productPageInfo)
        }
    }

    override suspend fun getAllUnsyncedProducts(): List<ProductPageInfo> {
        return withContext(dispatcher) {
            return@withContext productDao.getUnsyncedProducts()
        }
    }

    override suspend fun getAllProducts(): List<ProductPageInfo> {
        return withContext(dispatcher) {
            return@withContext productDao.getAllProducts()
        }
    }

}