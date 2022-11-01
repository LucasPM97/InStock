package com.lucas.instock.useCases.formatPageContent

import com.lucas.instock.data.local.IPageConfigLocalDataSource
import com.lucas.instock.data.model.PageConfig

class PageConfigLocalDataSourceMock : IPageConfigLocalDataSource {

    var failNextProcess = false

    fun failNextProcess() {
        failNextProcess = true
    }

    fun successNextProcess() {
        failNextProcess = false
    }

    override suspend fun addOrUpdatePageConfig(pageConfig: PageConfig) {}

    override suspend fun getPageConfigByProductId(productId: Int): PageConfig? {
        return if (failNextProcess)
            null
        else
            Consts.Models.PageConfig
    }
}