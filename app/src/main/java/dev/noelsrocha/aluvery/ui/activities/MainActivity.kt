package dev.noelsrocha.aluvery.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dev.noelsrocha.aluvery.ui.components.shared.App
import dev.noelsrocha.aluvery.ui.screens.HomeScreen
import dev.noelsrocha.aluvery.ui.viewmodels.HomeScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFabClick = {
                startActivity(Intent(this, ProductFormActivity::class.java))
            }, content = {
                val viewModel by viewModels<HomeScreenViewModel>()

                HomeScreen(viewModel)
            })
        }
    }
}