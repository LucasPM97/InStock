package com.lucas.instock.domain.format

import com.lucas.instock.data.local.IPageConfigLocalDataSource
import com.lucas.instock.data.model.PageConfig
import com.lucas.instock.data.model.ProductPageInfo
import com.lucas.instock.data.model.ProductSyncState
import com.lucas.instock.data.model.ProductUrlType
import com.lucas.instock.di.DefaultDispatcher
import com.lucas.instock.domain.models.format.findElement
import com.lucas.instock.domain.models.format.jsoupElement
import com.lucas.instock.domain.models.format.mapPath
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.nodes.Element
import javax.inject.Inject

interface IFormatProductPageContentUseCase {

    suspend operator fun invoke(productId: Int, pageContent: String): ProductPageInfo
}

class FormatProductPageContentUseCase @Inject constructor(
    private val pageConfigLocalDataSource: IPageConfigLocalDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IFormatProductPageContentUseCase {

    override suspend operator fun invoke(productId: Int, pageContent: String): ProductPageInfo {
        return withContext(dispatcher) {
            val pageConfig = getPageConfigByProductId(productId)
            return@withContext formatPageContentByPageConfig(pageContent, pageConfig)
        }
    }

    private suspend fun getPageConfigByProductId(productId: Int): PageConfig {
        return pageConfigLocalDataSource.getPageConfigByProductId(productId)
            ?: throw Exception("There's not a page configuration for this product")
    }

    protected fun formatPageContentByPageConfig(
        pageContent: String, pageConfig: PageConfig
    ): ProductPageInfo {

        val isValid = checkIsValidContent(pageContent)

        if (!isValid) {
            throw Exception("Not valid content")
        }

        val productRootElement = pageContent.jsoupElement {
            findElement(
                mapPath(pageConfig.rootElementPath)
            )
        } ?: throw Exception("Root element not found")

        val productNameElement = findElementByPath(
            productRootElement,
            pageConfig.productNameElementPath
        ) ?: throw Exception("Name element not found")

        val productImageElement = findElementByPath(
            productRootElement,
            pageConfig.imageElementPath
        ) ?: throw Exception("Image element not found")

        val productPriceElement = findElementByPath(
            productRootElement,
            pageConfig.priceElementPath
        ) ?: throw Exception("Price element not found")

        val productStockElement = findElementByPath(
            productRootElement,
            pageConfig.stockElementPath
        )

        //TODO: Separate value from HTML elements
        return ProductPageInfo(
            productId = 0,
            pageName = "Page Name",
            urlType = ProductUrlType.WebPage,
            synced = false,
            syncState = ProductSyncState.Synced,
            url = "https://entelequia.com.ar/producto/persona-3-05_26846"
        )
//        TODO("Use the PageConfig to know the rules to get the needed information")
//        TODO("Get product current status")
//        TODO("Get product current price")
    }

    private fun findElementByPath(parent: Element, path: String): Element? = parent.findElement(
        mapPath(path)
    )

    private fun checkIsValidContent(pageContent: String): Boolean {

        if (pageContent.isEmpty()) {
            return false
        }

        return true
    }

}