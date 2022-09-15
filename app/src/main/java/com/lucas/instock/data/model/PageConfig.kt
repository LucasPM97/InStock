package com.lucas.instock.data.model

data class PageConfig(
    val pageName: String,
    val pageDomain: String,
    val priceString: String,
    val stockString: String?,
    val preOrderString: String?,
    val preOwnedUrl: String?,
    val wishListUrl: String?,
    val wishListItemString: String?
)
