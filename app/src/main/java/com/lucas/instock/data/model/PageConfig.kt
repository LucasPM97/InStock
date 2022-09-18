package com.lucas.instock.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pageConfig")
data class PageConfig(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val pageName: String,
    val pageDomain: String,
    val priceString: String,
    val stockString: String?,
    val preOrderString: String?,
    val preOwnedUrl: String?,
    val wishListUrl: String?,
    val wishListItemString: String?
)
