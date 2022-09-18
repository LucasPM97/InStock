package com.lucas.instock.data.local

import com.lucas.instock.data.local.database.pageConfig.PageConfigDao
import com.lucas.instock.data.model.PageConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface IPageConfigLocalDataSource {
    suspend fun addOrUpdatePageConfig(pageConfig: PageConfig)
    suspend fun getPageConfigByProductId(productId: Int): PageConfig?
}

class PageConfigLocalDataSource(
    private val pageConfigDao: PageConfigDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
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