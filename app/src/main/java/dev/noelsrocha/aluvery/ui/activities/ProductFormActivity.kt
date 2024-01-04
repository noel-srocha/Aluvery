package dev.noelsrocha.aluvery.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import dev.noelsrocha.aluvery.data.daos.ProductDAO
import dev.noelsrocha.aluvery.models.Product
import dev.noelsrocha.aluvery.ui.screens.ProductFormScreen
import dev.noelsrocha.aluvery.ui.theme.AluveryTheme

class ProductFormActivity : ComponentActivity() {
    private val productDAO = ProductDAO()

    override fun onResume() {
        super.onResume()
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen(onSaveClick = { product: Product ->
                        productDAO.save(product)
                        finish()
                    })
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