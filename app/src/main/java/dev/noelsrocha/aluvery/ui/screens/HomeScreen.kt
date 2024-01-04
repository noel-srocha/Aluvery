package dev.noelsrocha.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.noelsrocha.aluvery.data.sampleCandies
import dev.noelsrocha.aluvery.data.sampleDrinks
import dev.noelsrocha.aluvery.data.sampleProducts
import dev.noelsrocha.aluvery.data.sampleSections
import dev.noelsrocha.aluvery.models.Product
import dev.noelsrocha.aluvery.ui.components.ProductItemCardExtended
import dev.noelsrocha.aluvery.ui.components.ProductsSection
import dev.noelsrocha.aluvery.ui.components.shared.SearchTextField
import dev.noelsrocha.aluvery.ui.states.HomeSreenUIState
import dev.noelsrocha.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(
    state: HomeSreenUIState = HomeSreenUIState()
) {
    val filteredProducts = state.filteredProducts

    Column {
        SearchTextField(
            label = "Produtos",
            placeholder = "O que você procura?",
            searchText = state.searchText,
            onSearchChange = state.onSearchChange
        )

        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            if (state.isShowSections()) {
                for ((title, products) in state.sections) {
                    item {
                        ProductsSection(title, products = products)
                    }
                }
            } else {
                items(filteredProducts) {
                    ProductItemCardExtended(
                        product = it,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(products: List<Product>) {
    val sections = mapOf(
        "Todos os Produtos" to products,
        "Promoções" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )

    var search by remember { mutableStateOf("") }

    val filteredProducts = remember(products, search) {
        if (search.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription(search)) +
                    products.filter(containsInNameOrDescription(search))
        } else
            emptyList()
    }

    val state = remember(products, search) {
        HomeSreenUIState(sections, filteredProducts, search, onSearchChange = { search = it })
    }

    HomeScreen(state)
}

fun containsInNameOrDescription(searchText: String) = {
    product: Product -> product.name.contains(searchText, ignoreCase = true) ||
    product.description?.contains(searchText, ignoreCase = true) ?: false
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(HomeSreenUIState(sampleSections))
        }
    }
}