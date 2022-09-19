package com.lucas.instock.data.local

import com.lucas.instock.data.local.database.product.ProductPageDao
import com.lucas.instock.data.model.ProductPageInfo
import com.lucas.instock.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface IProductLocalDataSource {
    suspend fun storeProductChanges(productPageInfo: ProductPageInfo)
    suspend fun getAllUnsyncedProducts(): List<ProductPageInfo>
    suspend fun getAllProducts(): List<ProductPageInfo>
    suspend fun getAllProductsFlow(): Flow<List<ProductPageInfo>>
}

class ProductLocalDataSource@Inject constructor(
    private val productDao: ProductPageDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default
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

    override suspend fun getAllProductsFlow(): Flow<List<ProductPageInfo>> =
        productDao.getAllProductsFlow()

}