package com.lucas.instock.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductPageInfo(
    @PrimaryKey(autoGenerate = true) val productId: Int,
    val pageName: String,
    val synced: Boolean,
    val syncState: ProductSyncState,
    val url: String,
    val notifyPriceChanged: Boolean,
    val inPreOrder: Boolean,
    val notifyPreOrder: Boolean,
    val inStock: Boolean,
    val notifyStockChanges: Boolean,
    val preOwnedInStock: Boolean,
    val notifyPreOwnedStockChanges: Boolean,
)

enum class ProductSyncState {
    Synced,
    IsLoading,
    Error
}
