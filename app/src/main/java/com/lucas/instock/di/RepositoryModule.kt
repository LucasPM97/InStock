package com.lucas.instock.di

import com.lucas.instock.data.repositories.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(repository: ProductRepository): IProductRepository

    @Binds
    @Singleton
    abstract fun bindProductPriceRepository(repository: ProductPriceRepository): IProductPriceRepository

    @Binds
    @Singleton
    abstract fun bindPageConfigRepository(repository: PageConfigRepository): IPageConfigRepository
}