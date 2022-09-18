package com.lucas.instock.ui.models

import com.lucas.instock.data.model.ProductSyncState

data class Product(
    val productId: Int,
    val name: String,
    val syncState: ProductSyncState,
    val url: String,
    val latestPrice: Float,
)
