package com.lucas.instock.data.local.database.product

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lucas.instock.data.model.ProductPageInfo

@Database(entities = [ProductPageInfo::class], version = 1, exportSchema = false)
abstract class ProductPageDatabase : RoomDatabase() {

    abstract fun dao(): ProductPageDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ProductPageDatabase? = null

        fun getDatabase(context: Context): ProductPageDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductPageDatabase::class.java,
                    "product"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}