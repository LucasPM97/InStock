package com.lucas.instock.data.local.database.price

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lucas.instock.data.model.ProductPrice

@Database(entities = [ProductPrice::class], version = 1, exportSchema = false)
abstract class ProductPricePageDatabase : RoomDatabase() {

    abstract fun dao(): ProductPriceDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ProductPricePageDatabase? = null

        fun getDatabase(context: Context): ProductPricePageDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductPricePageDatabase::class.java,
                    "price"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}