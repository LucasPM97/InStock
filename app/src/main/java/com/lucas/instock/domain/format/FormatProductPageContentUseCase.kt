package com.lucas.instock.domain.format

import com.lucas.instock.data.model.PageConfig
import com.lucas.instock.di.DefaultDispatcher
import com.lucas.instock.domain.models.format.findElement
import com.lucas.instock.domain.models.format.jsoupElement
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IFormatProductPageContentUseCase {

    suspend operator fun invoke(
        pageContent: String,
        pageConfig: PageConfig
    ): MappedProductPageContent
}

class FormatProductPageContentUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : IFormatProductPageContentUseCase {

    override suspend operator fun invoke(
        pageContent: String,
        pageConfig: PageConfig
    ): MappedProductPageContent {
        return withContext(dispatcher) {
            return@withContext formatPageContentByPageConfig(pageContent, pageConfig)
        }
    }

    protected fun formatPageContentByPageConfig(
        pageContent: String, pageConfig: PageConfig
    ): MappedProductPageContent {

        val isValid = checkIsValidContent(pageContent)

        if (!isValid) {
            throw Exception("Not valid content")
        }

        val productRootElement = pageContent.jsoupElement {
            findElement(
                pageConfig.rootElementPath
            )
        } ?: throw Exception("Root element not found")

        val productNameElement = productRootElement.findElement(
            pageConfig.productNameElementPath
        ) ?: throw Exception("Name element not found")

        val productImageElement = productRootElement.findElement(
            pageConfig.imageElementPath
        ) ?: throw Exception("Image element not found")

        val productPriceElement = productRootElement.findElement(
            pageConfig.priceElementPath
        ) ?: throw Exception("Price element not found")

        val productStockElement = productRootElement.findElement(
            pageConfig.stockElementPath
        )


        return MappedProductPageContent(
            name = productNameElement.text(),
            imageUrl = productImageElement.absUrl("src"),
            price = productPriceElement.text(),
            hasStock = productStockElement != null && productStockElement.text().isNotEmpty()
        )
    }

    private fun checkIsValidContent(pageContent: String): Boolean {

        if (pageContent.isEmpty()) {
            return false
        }

        return true
    }
}

data class MappedProductPageContent(
    val name: String,
    val price: String,
    val imageUrl: String,
    val hasStock: Boolean = false
)