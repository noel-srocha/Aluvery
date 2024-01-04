package dev.noelsrocha.aluvery.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.noelsrocha.aluvery.data.daos.ProductDAO
import dev.noelsrocha.aluvery.ui.components.shared.App
import dev.noelsrocha.aluvery.ui.screens.HomeScreen

class MainActivity : ComponentActivity() {
    private val productDAO = ProductDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFabClick = {
                startActivity(Intent(this, ProductFormActivity::class.java))
            }, content = {
                val products = productDAO.getProducts()

                HomeScreen(products)
            })
        }
    }
}