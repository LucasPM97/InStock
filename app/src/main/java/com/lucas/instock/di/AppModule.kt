package com.lucas.instock.di

import android.content.Context
import androidx.room.Room
import com.lucas.instock.data.local.database.pageConfig.PageConfigDao
import com.lucas.instock.data.local.database.pageConfig.PageConfigDatabase
import com.lucas.instock.data.local.database.price.ProductPriceDao
import com.lucas.instock.data.local.database.price.ProductPricePageDatabase
import com.lucas.instock.data.local.database.product.ProductPageDao
import com.lucas.instock.data.local.database.product.ProductPageDatabase
import com.lucas.instock.data.remote.CustomApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePageConfigDatabase(@ApplicationContext appContext: Context): PageConfigDatabase {
        return Room.databaseBuilder(
            appContext,
            PageConfigDatabase::class.java,
            "pageConfig"
        ).build()
    }

    @Provides
    @Singleton
    fun providePageConfigDao(database: PageConfigDatabase): PageConfigDao {
        return database.dao()
    }

    @Provides
    @Singleton
    fun providePriceDatabase(@ApplicationContext appContext: Context): ProductPricePageDatabase {
        return Room.databaseBuilder(
            appContext,
            ProductPricePageDatabase::class.java,
            "price"
        ).build()
    }

    @Provides
    @Singleton
    fun providePriceDao(database: ProductPricePageDatabase): ProductPriceDao {
        return database.dao()
    }

    @Provides
    @Singleton
    fun provideProductPageDatabase(@ApplicationContext appContext: Context): ProductPageDatabase {
        return Room.databaseBuilder(
            appContext,
            ProductPageDatabase::class.java,
            "product"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductPageDao(database: ProductPageDatabase): ProductPageDao {
        return database.dao()
    }

    @Provides
    @Singleton
    fun provideCustomApi(): CustomApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CustomApi::class.java)
    }

}