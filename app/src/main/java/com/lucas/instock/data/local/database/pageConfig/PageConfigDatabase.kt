package com.lucas.instock.data.local.database.pageConfig

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucas.instock.data.model.PageConfig
import com.lucas.instock.data.model.ProductAndPageConfig

@Database(
    entities = [PageConfig::class, ProductAndPageConfig::class],
    version = 1,
    exportSchema = false
)
abstract class PageConfigDatabase : RoomDatabase() {

    abstract fun dao(): PageConfigDao
}