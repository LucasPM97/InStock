package com.lucas.instock.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pageConfig")
data class PageConfig(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val pageName: String,
    val pageDomain: String,
    val rootElementPath: String,
    val productNameElementPath: String,
    val imageElementPath: String,
    val priceElementPath: String,
    val stockElementPath: String,
    val preOrderElementPath: String? = null,
    val preOwnedUrl: String? = null,
    val wishListUrl: String? = null,
    val wishListItemString: String? = null
)
