package dev.noelsrocha.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.noelsrocha.aluvery.data.daos.ProductDAO
import dev.noelsrocha.aluvery.data.sampleCandies
import dev.noelsrocha.aluvery.data.sampleDrinks
import dev.noelsrocha.aluvery.data.sampleProducts
import dev.noelsrocha.aluvery.models.Product
import dev.noelsrocha.aluvery.ui.states.HomeScreenUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val productDAO = ProductDAO()

    private val _uiState: MutableStateFlow<HomeScreenUIState> = MutableStateFlow(HomeScreenUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchChange = {
                    _uiState.value = _uiState.value.copy(
                        searchText = it,
                        filteredProducts = filterProducts(it)
                    )
                }
            )
        }

        viewModelScope.launch {
            productDAO.getProducts().collect { products ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "All Products" to products,
                        "On Sale" to sampleDrinks + sampleCandies,
                        "Candies" to sampleCandies,
                        "Drinks" to sampleDrinks
                    ),
                    filteredProducts = filterProducts(_uiState.value.searchText)
                )
            }
        }
    }

    private fun filterProducts(searchText: String): List<Product> {
        return if (searchText.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription(searchText)) +
                    productDAO.getProducts().value.filter(containsInNameOrDescription(searchText))
        } else {
            emptyList()
        }
    }

    private fun containsInNameOrDescription(searchText: String) = {
            product: Product -> product.name.contains(searchText, ignoreCase = true) ||
            product.description?.contains(searchText, ignoreCase = true) ?: false
    }
}