package com.lucas.instock.data.local.database.pageConfig

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lucas.instock.data.model.ProductAndPageConfig

@Database(entities = [ProductAndPageConfig::class], version = 1, exportSchema = false)
abstract class ProductPageConfigDatabase : RoomDatabase() {

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ProductPageConfigDatabase? = null

        fun getDatabase(context: Context): ProductPageConfigDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductPageConfigDatabase::class.java,
                    "productPageConfig"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}