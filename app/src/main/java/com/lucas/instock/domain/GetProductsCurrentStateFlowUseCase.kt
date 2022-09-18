package com.lucas.instock.domain

import com.lucas.instock.data.model.ProductPageInfo
import com.lucas.instock.data.model.ProductPrice
import com.lucas.instock.data.repositories.IProductPriceRepository
import com.lucas.instock.data.repositories.IProductRepository
import com.lucas.instock.ui.models.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

interface IGetProductsCurrentStateFlowUseCase {
    suspend operator fun invoke(scope: CoroutineScope): StateFlow<List<Product>>
}

class GetProductsCurrentStateFlowUseCase(
    private val productRepository: IProductRepository,
    private val priceRepository: IProductPriceRepository,
) : IGetProductsCurrentStateFlowUseCase {

    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    private val productList = _productList.asStateFlow()

    override suspend operator fun invoke(scope: CoroutineScope): StateFlow<List<Product>> {

        val products = productRepository.getProductsFlow()
        val prices = priceRepository.getLatestPricePerProductFlow()

        products.combine(prices) { products, prices ->
            products?.let {
                if (products.isNotEmpty()) {
                    _productList.update {
                        products.toProductList(prices)
                    }
                }
            }
        }.launchIn(scope)

        return productList
    }

    fun List<ProductPageInfo>.toProductList(prices: List<ProductPrice>?): List<Product> {
        return this.map {

            val latestPrice = prices?.firstOrNull { price ->
                price.productId == it.productId
            }?.price ?: 0f

            Product(
                productId = it.productId,
                name = it.pageName,
                syncState = it.syncState,
                url = it.url,
                latestPrice = latestPrice
            )
        }
    }

}