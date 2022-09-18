package com.lucas.instock.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "productPageConfig")
data class ProductAndPageConfig(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val productId: Int,
    val pageConfigId: Int

)
