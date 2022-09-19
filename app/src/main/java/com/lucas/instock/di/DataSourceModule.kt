package com.lucas.instock.di

import com.lucas.instock.data.local.*
import com.lucas.instock.data.remote.IProductRemoteDataSource
import com.lucas.instock.data.remote.ProductRemoteDataSource
import com.lucas.instock.data.repositories.IProductRepository
import com.lucas.instock.data.repositories.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindPriceLocalDataSource(dataSource: PriceLocalDataSource): IPriceLocalDataSource

    @Binds
    @Singleton
    abstract fun bindPageConfigLocalDataSource(dataSource: PageConfigLocalDataSource): IPageConfigLocalDataSource

    @Binds
    @Singleton
    abstract fun bindProductLocalDataSource(dataSource: ProductLocalDataSource): IProductLocalDataSource

    @Binds
    @Singleton
    abstract fun bindProductRemoteDataSource(dataSource: ProductRemoteDataSource): IProductRemoteDataSource

}