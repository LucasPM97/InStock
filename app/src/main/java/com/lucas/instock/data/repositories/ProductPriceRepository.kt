package com.lucas.instock.data.repositories

import com.lucas.instock.data.local.IPriceLocalDataSource
import com.lucas.instock.data.model.ProductPrice
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface IProductPriceRepository {
    suspend fun getProductPriceHistory(productId: Int): List<ProductPrice>
    suspend fun addNewPrice(price: ProductPrice)
    suspend fun getProductLatestPrice(productId: Int): ProductPrice
    suspend fun deleteProductPriceHistory(productId: Int)
    suspend fun deleteAll()
}

class ProductPriceRepository(
    private val localDataSource: IPriceLocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IProductPriceRepository {
    override suspend fun getProductPriceHistory(productId: Int): List<ProductPrice> {
        return withContext(dispatcher) {
            return@withContext localDataSource.getPriceHistoryByProductId(productId)
        }
    }

    override suspend fun addNewPrice(price: ProductPrice) {
        return withContext(dispatcher) {
            return@withContext localDataSource.storeLatestPrice(price)
        }
    }

    override suspend fun getProductLatestPrice(productId: Int): ProductPrice {
        return withContext(dispatcher) {
            return@withContext localDataSource.getLatestPriceByProductId(productId)
        }
    }

    override suspend fun deleteProductPriceHistory(productId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

}