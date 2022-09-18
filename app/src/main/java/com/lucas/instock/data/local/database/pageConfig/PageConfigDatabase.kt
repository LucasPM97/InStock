package com.lucas.instock.data.local.database.pageConfig

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PageConfigDatabase? = null

        fun getDatabase(context: Context): PageConfigDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PageConfigDatabase::class.java,
                    "pageConfig"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}