package com.lucas.instock.domain.format

import com.lucas.instock.data.local.PageConfigLocalDataSource
import com.lucas.instock.data.model.PageConfig
import com.lucas.instock.data.model.ProductPageInfo
import com.lucas.instock.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IFormatProductPageContentUseCase {

    suspend fun getPageConfigByProductId(productId: Int): PageConfig

    suspend fun formatPageContentByPageConfig(
        pageContent: String,
        pageConfig: PageConfig
    ): ProductPageInfo
}

class FormatProductPageContentUseCase@Inject constructor(
    private val pageConfigLocalDataSource: PageConfigLocalDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IFormatProductPageContentUseCase {

    suspend operator fun invoke(productId: Int, pageContent: String): ProductPageInfo {
        return withContext(dispatcher) {
            val pageConfig = getPageConfigByProductId(productId)
            return@withContext formatPageContentByPageConfig(pageContent, pageConfig)
        }
    }

    override suspend fun getPageConfigByProductId(productId: Int): PageConfig {
        val pageConfig =
            pageConfigLocalDataSource.getPageConfigByProductId(productId)
                ?: throw Exception("There's not a page configuration for this product")

        return pageConfig
    }

    override suspend fun formatPageContentByPageConfig(
        pageContent: String,
        pageConfig: PageConfig
    ): ProductPageInfo {
        TODO("Use the PageConfig to know the rules to get the needed information")
        TODO("Get product current status")
        TODO("Get product current price")
    }

}