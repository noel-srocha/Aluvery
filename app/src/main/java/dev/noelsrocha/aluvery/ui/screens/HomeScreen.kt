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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.noelsrocha.aluvery.data.sampleSections
import dev.noelsrocha.aluvery.ui.components.ProductItemCardExtended
import dev.noelsrocha.aluvery.ui.components.ProductsSection
import dev.noelsrocha.aluvery.ui.components.shared.SearchTextField
import dev.noelsrocha.aluvery.ui.states.HomeScreenUIState
import dev.noelsrocha.aluvery.ui.theme.AluveryTheme
import dev.noelsrocha.aluvery.ui.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(
    state: HomeScreenUIState = HomeScreenUIState()
) {
    val filteredProducts = state.filteredProducts

    Column {
        SearchTextField(
            label = "Products",
            placeholder = "What are you looking for?",
            searchText = state.searchText,
            onSearchChange = state.onSearchChange
        )

        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            if (state.isShowSections) {
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
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val state by viewModel.uiState.collectAsState()

    HomeScreen(state)
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(HomeScreenUIState(sampleSections))
        }
    }
}