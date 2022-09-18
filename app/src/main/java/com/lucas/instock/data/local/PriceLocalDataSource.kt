package com.lucas.instock.data.local

import com.lucas.instock.data.local.database.price.ProductPriceDao
import com.lucas.instock.data.model.ProductPrice
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface IPriceLocalDataSource {
    suspend fun storeLatestPrice(price: ProductPrice)
    suspend fun getLatestPriceByProductId(productId: Int): ProductPrice
    suspend fun getLatestPricePerProductFlow(): Flow<List<ProductPrice>>
    suspend fun getPriceHistoryByProductId(productId: Int): List<ProductPrice>
}

class PriceLocalDataSource(
    private val priceDao: ProductPriceDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IPriceLocalDataSource {
    override suspend fun storeLatestPrice(price: ProductPrice) {
        return withContext(dispatcher) {
            return@withContext priceDao.insert(price)
        }
    }

    override suspend fun getLatestPricePerProductFlow(): Flow<List<ProductPrice>> =
        priceDao.getLatestPricePerProductFlow()

    override suspend fun getLatestPriceByProductId(productId: Int): ProductPrice {
        return withContext(dispatcher) {
            return@withContext priceDao.getLatestProductPrice(productId)
        }
    }

    override suspend fun getPriceHistoryByProductId(productId: Int): List<ProductPrice> {
        return withContext(dispatcher) {
            return@withContext priceDao.getProductPriceHistory(productId)
        }
    }
}