package com.lucas.instock.domain

import com.lucas.instock.data.model.ProductPageInfo
import com.lucas.instock.data.model.ProductSyncState
import com.lucas.instock.data.model.ProductUrlType
import com.lucas.instock.data.repositories.IProductPriceRepository
import com.lucas.instock.data.repositories.IProductRepository
import com.lucas.instock.di.DefaultDispatcher
import com.lucas.instock.domain.format.FormatProductPageContentUseCase
import com.lucas.instock.domain.format.IFormatProductPageContentUseCase
import com.lucas.instock.ui.models.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IFetchUnsyncedProductsUseCase {
    suspend operator fun invoke()
    suspend fun fetchUnsyncProducts()
    suspend fun storeProductChanges(productInfo: ProductPageInfo)
    suspend fun fetchProduct(productInfo: ProductPageInfo)
}

class FetchUnsyncedProductsUseCase @Inject constructor(
    private val productRepository: IProductRepository,
    private val priceRepository: IProductPriceRepository,
    private val formatProductPageContentUseCase: IFormatProductPageContentUseCase,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IFetchUnsyncedProductsUseCase {
    override suspend operator fun invoke() {
        return withContext(dispatcher) {
            return@withContext fetchUnsyncProducts()
        }
    }

    override suspend fun fetchUnsyncProducts() {
        return withContext(dispatcher) {
            val unsyncedProducts = listOf(
                ProductPageInfo(
                    productId = 0,
                    pageName = "Page Name",
                    urlType = ProductUrlType.API,
                    synced = false,
                    syncState = ProductSyncState.Synced,
                    url = "https://api.amiami.com/api/v1.0/item?gcode=FIGURE-011624&lang=eng"
//                  url = "https://www.amiami.com/eng/detail/?gcode=FIGURE-139629"
//                  url = "https://entelequia.com.ar/producto/persona-3-05_26846"
//                  url = "https://reactjs.org/"
                )
            )//productRepository.getUnsyncedProducts()
            if (unsyncedProducts.isEmpty()) {
                //TODO: Stop Workmanager schedule jobs
                return@withContext
            }

            unsyncedProducts.forEach {
                try {
                    fetchProduct(it)
                } catch (ex: Exception) {
                    val productInfo = it.copy(
                        synced = false,
                        syncState = ProductSyncState.Error
                    )
                    storeProductChanges(productInfo)
                }
            }
        }
    }

    override suspend fun storeProductChanges(productInfo: ProductPageInfo) {
        productRepository.updateProduct(productInfo)
    }

    override suspend fun fetchProduct(productInfo: ProductPageInfo) {
        val pageContent = productRepository
            .getProductPageContent(productInfo.url, productInfo.urlType)
            ?: throw Exception("There was an error trying to connect with page")

        val updatedProductPageInfo =
            formatProductPageContentUseCase(productInfo.productId, pageContent)

        TODO("Notify changes if is needed")
        TODO("Update price if is needed")
        storeProductChanges(updatedProductPageInfo)

    }
}