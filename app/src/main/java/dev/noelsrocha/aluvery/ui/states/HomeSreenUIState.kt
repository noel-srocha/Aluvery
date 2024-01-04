package dev.noelsrocha.aluvery.ui.states

import dev.noelsrocha.aluvery.models.Product

class HomeSreenUIState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val filteredProducts: List<Product> = emptyList(),
    var searchText: String = "",
    val onSearchChange: (value: String) -> Unit = {}
) {
    fun isShowSections(): Boolean = searchText.isBlank()
}