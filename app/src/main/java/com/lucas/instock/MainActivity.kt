package com.lucas.instock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lucas.instock.ui.screens.productList.ProductListScreen
import com.lucas.instock.ui.theme.InStockTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InStockTheme {
                // A surface container using the 'background' color from the theme
                ProductListScreen()
            }
        }
    }
}