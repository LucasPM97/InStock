package com.lucas.instock.ui.screens.productList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucas.instock.domain.IGetProductsCurrentStateFlowUseCase
import com.lucas.instock.ui.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsCurrentStateFlowUseCase: IGetProductsCurrentStateFlowUseCase
) : ViewModel() {

    lateinit var productList: StateFlow<List<Product>>

    init {
        viewModelScope.launch {
            productList = getProductsCurrentStateFlowUseCase(viewModelScope)
        }
    }

}