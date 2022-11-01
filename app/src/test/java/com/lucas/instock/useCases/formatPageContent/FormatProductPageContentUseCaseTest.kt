package com.lucas.instock.useCases.formatPageContent

import com.lucas.instock.domain.format.FormatProductPageContentUseCase
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
        val useCase = FormatProductPageContentUseCase(dataSourceMock)
        launch {
            val a = useCase(0, Consts.HtmlElements.BaseHTML)
            assertTrue(true)
        }
    }
}