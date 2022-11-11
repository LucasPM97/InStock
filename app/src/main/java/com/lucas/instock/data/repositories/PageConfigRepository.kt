package com.lucas.instock.data.repositories

import com.lucas.instock.data.model.PageConfig
import com.lucas.instock.data.local.IPageConfigLocalDataSource
import com.lucas.instock.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface IPageConfigRepository {
    suspend fun getPageConfigByProductId(productId: Int): PageConfig?
}

class PageConfigRepository @Inject constructor(
    private val localDataSource: IPageConfigLocalDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IPageConfigRepository {

    override suspend fun getPageConfigByProductId(productId: Int): PageConfig? =
        localDataSource.getPageConfigByProductId(
            productId
        )
}