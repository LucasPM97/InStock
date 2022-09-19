package com.lucas.instock.di

import com.lucas.instock.domain.FetchUnsyncedProductsUseCase
import com.lucas.instock.domain.GetProductsCurrentStateFlowUseCase
import com.lucas.instock.domain.IFetchUnsyncedProductsUseCase
import com.lucas.instock.domain.IGetProductsCurrentStateFlowUseCase
import com.lucas.instock.domain.format.FormatProductPageContentUseCase
import com.lucas.instock.domain.format.IFormatProductPageContentUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    @Singleton
    abstract fun bindFormatProductPageContentUseCase(
        useCase: FormatProductPageContentUseCase
    ): IFormatProductPageContentUseCase

    @Binds
    @Singleton
    abstract fun bindFetchUnsyncedProductsUseCase(
        useCase: FetchUnsyncedProductsUseCase
    ): IFetchUnsyncedProductsUseCase

    @Binds
    @Singleton
    abstract fun bindGetProductsCurrentStateFlowUseCase(
        useCase: GetProductsCurrentStateFlowUseCase
    ): IGetProductsCurrentStateFlowUseCase
}