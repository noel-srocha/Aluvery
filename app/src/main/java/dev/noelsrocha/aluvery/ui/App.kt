package dev.noelsrocha.aluvery.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.noelsrocha.aluvery.ui.screens.HomeScreen
import dev.noelsrocha.aluvery.ui.theme.AluveryTheme

@Composable
fun App() {
    AluveryTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen()
        }
    }
}