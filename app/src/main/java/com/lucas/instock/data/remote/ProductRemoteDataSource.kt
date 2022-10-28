package com.lucas.instock.data.remote

import com.lucas.instock.di.DefaultDispatcher
import it.skrape.core.htmlDocument
import it.skrape.fetcher.BrowserFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.matchers.toBe
import it.skrape.selects.html5.h1
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface IProductRemoteDataSource {
    suspend fun getUrlApiContent(requestUrl: String): String?
    suspend fun getUrlWebPageContent(requestUrl: String): String?
}

class ProductRemoteDataSource @Inject constructor(
    private val customApi: CustomApi,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IProductRemoteDataSource {

    override suspend fun getUrlApiContent(requestUrl: String): String? {
        return withContext(dispatcher) {
            return@withContext customApi.getUrlContent(requestUrl)
        }
    }

    override suspend fun getUrlWebPageContent(requestUrl: String): String? {
        return withContext(dispatcher) {
            return@withContext skrape(CustomBrowserFetcher) {
                request {
                    url = requestUrl
                }
                response {
                    this.responseBody
                }
            }
        }
    }

}