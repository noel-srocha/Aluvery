package dev.noelsrocha.aluvery.data.daos

import dev.noelsrocha.aluvery.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDAO {
    companion object {
        private val products = MutableStateFlow<List<Product>>(emptyList())
    }

    fun getProducts() = products.asStateFlow()

    fun save(product: Product) {
        products.value = products.value + product
    }
}