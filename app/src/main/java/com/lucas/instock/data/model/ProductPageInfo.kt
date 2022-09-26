package com.lucas.instock.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductPageInfo(
    @PrimaryKey(autoGenerate = true) val productId: Int,
    val pageName: String,
    val urlType: ProductUrlType = ProductUrlType.WebPage,
    val synced: Boolean = false,
    val syncState: ProductSyncState = ProductSyncState.UnSynced,
    val url: String,
    val notifyPriceChanged: Boolean = false,
    val inPreOrder: Boolean = false,
    val notifyPreOrder: Boolean = false,
    val inStock: Boolean = false,
    val notifyStockChanges: Boolean = false,
    val preOwnedInStock: Boolean = false,
    val notifyPreOwnedStockChanges: Boolean = false,
)

enum class ProductSyncState {
    Synced,
    UnSynced,
    IsLoading,
    Error
}

enum class ProductUrlType {
    API,
    WebPage
}
