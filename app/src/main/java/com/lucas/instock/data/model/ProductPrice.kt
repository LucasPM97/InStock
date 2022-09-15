package com.lucas.instock.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "price_table")
data class ProductPrice(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val productId: Int,
    val price: Float,
    val createdAt: Date
)
