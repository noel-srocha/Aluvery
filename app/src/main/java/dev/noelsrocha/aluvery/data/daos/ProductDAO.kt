package dev.noelsrocha.aluvery.data.daos

import androidx.compose.runtime.mutableStateListOf
import dev.noelsrocha.aluvery.data.sampleProducts
import dev.noelsrocha.aluvery.models.Product

class ProductDAO {
    companion object {
        private val products = mutableStateListOf(*sampleProducts.toTypedArray())
    }

    fun getProducts() = products.toList()

    fun save(product: Product) {
        products.add(product)
    }
}