package dev.noelsrocha.aluvery.ui.states

data class ProductFormUIState(
    val productName: String = "",
    val productDescription: String = "",
    val productImageUrl: String = "",
    val productPrice: String = "",
    val onProductNameChange: (value: String) -> Unit = {},
    val onProductDescriptionChange: (value: String) -> Unit = {},
    val onProductImageUrlChange: (value: String) -> Unit = {},
    val onProductPriceChange: (value: String) -> Unit = {},
) {
    val isShowPreview: Boolean get() = productImageUrl.isNotBlank()
}