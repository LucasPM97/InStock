package com.lucas.instock.domain.format

import android.os.Build
import android.text.Html
import com.lucas.instock.data.local.PageConfigLocalDataSource
import com.lucas.instock.data.model.PageConfig
import com.lucas.instock.data.model.ProductPageInfo
import com.lucas.instock.data.model.ProductSyncState
import com.lucas.instock.data.model.ProductUrlType
import com.lucas.instock.di.DefaultDispatcher
import com.lucas.instock.domain.models.format.HtmlString
import com.lucas.instock.domain.models.format.PathElement
import com.lucas.instock.domain.models.format.findElement
import com.lucas.instock.domain.models.format.htmlString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IFormatProductPageContentUseCase {

    suspend operator fun invoke(productId: Int, pageContent: String): ProductPageInfo
}

class FormatProductPageContentUseCase @Inject constructor(
    private val pageConfigLocalDataSource: PageConfigLocalDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IFormatProductPageContentUseCase {

    override suspend operator fun invoke(productId: Int, pageContent: String): ProductPageInfo {
        return withContext(dispatcher) {
            val pageConfig = getPageConfigByProductId(productId)
            return@withContext formatPageContentByPageConfig(pageContent, pageConfig)
        }
    }

    fun getPageConfigByProductId(productId: Int): PageConfig {
        val pageConfig = PageConfig(
            id = 0,
            pageName = "Entelequia",
            pageDomain = "https://entelequia.com.ar/",
            rootElementPath = "div.root/div.product inner-page max-container container-fluid/div.row",
            productNameElementPath = "div.product-info mt-5 mt-sm-0 col-md-6 col-12/h4",
            imageElementPath = "div.text-center active carousel-item/figure.iiz  /div/img.iiz__img  ",
            priceElementPath = "div.product-info mt-5 mt-sm-0 col-md-6 col-12/p.product-price mb-4",
            stockElementPath = "div.product-info mt-5 mt-sm-0 col-md-6 col-12/div.cart-control/button.add-to-cart button-loader"
        )
//            pageConfigLocalDataSource.getPageConfigByProductId(productId)
//                ?: throw Exception("There's not a page configuration for this product")

        return pageConfig
    }

    fun formatPageContentByPageConfig(
        pageContent: String, pageConfig: PageConfig
    ): ProductPageInfo {

        val isValid = checkIsValidContent(pageContent)

        if (!isValid) {
            throw Exception("Not valid content")
        }

        val productRootElement = pageContent.htmlString {
            subStringFromElementPath(pageConfig.rootElementPath)
        } ?: throw Exception("Root element not found")

        val productNameElement =
            productRootElement.subStringFromElementPath(pageConfig.productNameElementPath)
                ?: throw Exception("Name element not found")

        val productImageElement =
            productRootElement.subStringFromElementPath(pageConfig.imageElementPath)
                ?: throw Exception("Image element not found")

        val productPriceElement =
            productRootElement.subStringFromElementPath(pageConfig.priceElementPath)
                ?: throw Exception("Price element not found")

        val productStockElement =
            productRootElement.subStringFromElementPath(pageConfig.stockElementPath)

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

    private fun HtmlString.subStringFromElementPath(elementPath: String): HtmlString? {

        val pathElements: List<PathElement> = elementPath.split("/").map {
            if (it.contains(".")) {
                it.split(".").let { (first, second) ->
                    PathElement(
                        first, second
                    )
                }
            } else {
                PathElement(
                    it,
                )
            }
        }

        return findElement(pathElements)
    }

    private fun checkIsValidContent(pageContent: String): Boolean {

        if (pageContent.isEmpty()) {
            return false
        }

        return true
    }

}