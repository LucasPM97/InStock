package com.lucas.instock.data.local.database.price

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucas.instock.data.model.ProductPrice

@Database(entities = [ProductPrice::class], version = 1, exportSchema = false)
abstract class ProductPricePageDatabase : RoomDatabase() {
    abstract fun dao(): ProductPriceDao
}