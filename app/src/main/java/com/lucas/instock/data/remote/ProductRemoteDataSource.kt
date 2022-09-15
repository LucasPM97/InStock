package com.lucas.instock.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface IProductRemoteDataSource {
    suspend fun getUrlContent(url: String): String
}

class ProductRemoteDataSource(
    private val customApi: CustomApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IProductRemoteDataSource {

    override suspend fun getUrlContent(url: String): String {
        return withContext(dispatcher) {
            return@withContext customApi.getUrlContent(url)
        }
    }

}