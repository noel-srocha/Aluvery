package dev.noelsrocha.aluvery.ui.states

import dev.noelsrocha.aluvery.models.Product
import java.math.BigDecimal

class ProductFormUIState(
    var productName: String = "",
    var productDescription: String = "",
    var productImageUrl: String = "",
    var productPrice: String = "",
    val onSaveClick: (product: Product) -> Unit = {}
) {
    fun isImageUrlValid(): Boolean = productImageUrl.isNotBlank()

    fun convertPrice(): BigDecimal {
        return try {
            BigDecimal(productPrice)
        } catch (ex: NumberFormatException) {
            BigDecimal.ZERO
        }
    }
}