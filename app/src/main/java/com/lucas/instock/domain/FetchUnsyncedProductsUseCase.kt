package com.lucas.instock.domain

import com.lucas.instock.data.model.ProductPageInfo
import com.lucas.instock.data.model.ProductSyncState
import com.lucas.instock.data.repositories.IProductPriceRepository
import com.lucas.instock.data.repositories.IProductRepository
import com.lucas.instock.di.DefaultDispatcher
import com.lucas.instock.domain.format.FormatProductPageContentUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IFetchUnsyncedProductsUseCase {
    suspend fun fetchUnsyncProducts()
    suspend fun storeProductChanges(productInfo: ProductPageInfo)
    suspend fun fetchProduct(productInfo: ProductPageInfo)
}

class FetchUnsyncedProductsUseCase@Inject constructor(
    private val productRepository: IProductRepository,
    private val priceRepository: IProductPriceRepository,
    private val formatProductPageContentUseCase: FormatProductPageContentUseCase,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IFetchUnsyncedProductsUseCase {
    suspend operator fun invoke() {
        return withContext(dispatcher) {
            return@withContext fetchUnsyncProducts()
        }
    }

    override suspend fun fetchUnsyncProducts() {
        return withContext(dispatcher) {
            val unsyncedProducts = productRepository.getUnsyncedProducts()
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
        val pageContet = productRepository.getProductPageContent(productInfo.url)
            ?: throw Exception("There was an error trying to connect with page")

        val updatedProductPageInfo =
            formatProductPageContentUseCase(productInfo.productId, pageContet)

        TODO("Notify changes if is needed")
        TODO("Update price if is needed")
        storeProductChanges(updatedProductPageInfo)

    }
}