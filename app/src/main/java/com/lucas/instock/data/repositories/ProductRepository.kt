package com.lucas.instock.data.repositories

import com.lucas.instock.data.local.IProductLocalDataSource
import com.lucas.instock.data.model.ProductPageInfo
import com.lucas.instock.data.remote.IProductRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

interface IProductRepository {
    suspend fun getAllProducts(): List<ProductPageInfo>
    suspend fun getUnsyncedProducts(): List<ProductPageInfo>
    suspend fun getProductPageContent(url: String): String?
    suspend fun deleteProduct(productId: Int)
    suspend fun deleteAllProducts()
}

class ProductRepository(
    private val remoteDataSource: IProductRemoteDataSource,
    private val localDataSource: IProductLocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IProductRepository {
    override suspend fun getAllProducts(): List<ProductPageInfo> {
        return withContext(dispatcher) {
            return@withContext localDataSource.getAllUnsyncedProducts()
        }
    }

    override suspend fun getUnsyncedProducts(): List<ProductPageInfo> {
        return withContext(dispatcher) {
            return@withContext localDataSource.getAllUnsyncedProducts()
        }
    }

    override suspend fun getProductPageContent(url: String): String? {
        return withContext(dispatcher) {
            val response = try {
                remoteDataSource.getUrlContent(url)
            } catch (e: Exception) {
                print(e)
                null
            }
            return@withContext response
        }
    }

    override suspend fun deleteProduct(productId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllProducts() {
        TODO("Not yet implemented")
    }

}