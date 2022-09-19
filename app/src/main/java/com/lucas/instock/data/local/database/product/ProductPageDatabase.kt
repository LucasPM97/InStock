package com.lucas.instock.data.local.database.product

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucas.instock.data.model.ProductPageInfo

@Database(entities = [ProductPageInfo::class], version = 1, exportSchema = false)
abstract class ProductPageDatabase : RoomDatabase() {

    abstract fun dao(): ProductPageDao
}