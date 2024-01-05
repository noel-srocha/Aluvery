package dev.noelsrocha.aluvery.ui.states

import dev.noelsrocha.aluvery.models.Product

data class HomeScreenUIState(
    var sections: Map<String, List<Product>> = emptyMap(),
    val filteredProducts: List<Product> = emptyList(),
    var searchText: String = "",
    val onSearchChange: (value: String) -> Unit = {}
) {
    val isShowSections: Boolean get() = searchText.isBlank()
}