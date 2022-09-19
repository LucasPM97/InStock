package com.lucas.instock.data.remote

import com.lucas.instock.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface IProductRemoteDataSource {
    suspend fun getUrlContent(url: String): String
}

class ProductRemoteDataSource@Inject constructor(
    private val customApi: CustomApi,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IProductRemoteDataSource {

    override suspend fun getUrlContent(url: String): String {
        return withContext(dispatcher) {
            return@withContext customApi.getUrlContent(url)
        }
    }

}