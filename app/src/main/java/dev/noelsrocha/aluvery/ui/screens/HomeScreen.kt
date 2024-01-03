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
import dev.noelsrocha.aluvery.data.sampleProducts
import dev.noelsrocha.aluvery.data.sampleSections
import dev.noelsrocha.aluvery.models.Product
import dev.noelsrocha.aluvery.ui.components.ProductItemCardExtended
import dev.noelsrocha.aluvery.ui.components.ProductsSection
import dev.noelsrocha.aluvery.ui.components.SearchTextField
import dev.noelsrocha.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(sections: Map<String, List<Product>> = emptyMap()) {
    var search by remember { mutableStateOf("") }

    val filteredProducts = remember(search) {
        if (search.isNotBlank()) {
            sampleProducts.filter {
                it.name.contains(search, ignoreCase = true) ||
                        it.description?.contains(search, ignoreCase = true) ?: false
            }
        } else emptyList()
    }

    Column {
        SearchTextField(searchText = search, onSearchChange = { search = it })

        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            if (search.isBlank()) {
                for ((title, products) in sections) {
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

@Preview
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(sampleSections)
        }
    }
}