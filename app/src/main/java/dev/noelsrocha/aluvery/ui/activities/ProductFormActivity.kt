package dev.noelsrocha.aluvery.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import dev.noelsrocha.aluvery.ui.screens.ProductFormScreen
import dev.noelsrocha.aluvery.ui.theme.AluveryTheme
import dev.noelsrocha.aluvery.ui.viewmodels.ProductFormScreenViewModel

class ProductFormActivity : ComponentActivity() {
    override fun onResume() {
        super.onResume()
        setContent {
            AluveryTheme {
                Surface {
                    val viewModel by viewModels<ProductFormScreenViewModel>()
                    ProductFormScreen(
                        viewModel,
                        onSaveClick = {
                            finish()
                        }
                    )
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen()
                }
            }
        }
    }
}