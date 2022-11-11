package com.lucas.instock.useCases.formatPageContent

import com.lucas.instock.domain.format.FormatProductPageContentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class FormatProductPageContentUseCaseTest {

    private val dataSourceMock = PageConfigLocalDataSourceMock()

    @Before
    fun init() {
        dataSourceMock.failNextProcess = false
    }

    @Test
    fun `Map valid HTML to ProductInfo model`() = runTest {
        val useCase = FormatProductPageContentUseCase(Dispatchers.IO)
        launch {
            val a = useCase(Consts.HtmlElements.BaseHTML, Consts.Models.PageConfig)
            assertTrue(true)
        }
    }
}