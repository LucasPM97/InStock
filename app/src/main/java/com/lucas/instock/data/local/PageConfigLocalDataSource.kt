package com.lucas.instock.data.local

import com.lucas.instock.data.local.database.pageConfig.PageConfigDao
import com.lucas.instock.data.model.PageConfig
import com.lucas.instock.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IPageConfigLocalDataSource {
    suspend fun addOrUpdatePageConfig(pageConfig: PageConfig)
    suspend fun getPageConfigByProductId(productId: Int): PageConfig?
}

class PageConfigLocalDataSource @Inject constructor(
    private val pageConfigDao: PageConfigDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IPageConfigLocalDataSource {
    override suspend fun addOrUpdatePageConfig(pageConfig: PageConfig) {
        return withContext(dispatcher) {
            return@withContext pageConfigDao.insert(pageConfig)
        }
    }

    override suspend fun getPageConfigByProductId(productId: Int): PageConfig? {
        return withContext(dispatcher) {
            return@withContext pageConfigDao.getPageConfigurationByProductId(productId)
        }
    }
}