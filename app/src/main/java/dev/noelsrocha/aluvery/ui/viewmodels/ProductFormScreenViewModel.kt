package dev.noelsrocha.aluvery.ui.viewmodels

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.noelsrocha.aluvery.data.daos.ProductDAO
import dev.noelsrocha.aluvery.data.sampleCandies
import dev.noelsrocha.aluvery.data.sampleDrinks
import dev.noelsrocha.aluvery.models.Product
import dev.noelsrocha.aluvery.ui.states.ProductFormUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormScreenViewModel : ViewModel() {
    private val productDAO = ProductDAO()
    private val _uiState: MutableStateFlow<ProductFormUIState> = MutableStateFlow(ProductFormUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onProductImageUrlChange = { _uiState.value = _uiState.value.copy(productImageUrl = it) },
                onProductNameChange = { _uiState.value = _uiState.value.copy(productName = it) },
                onProductDescriptionChange = { _uiState.value = _uiState.value.copy(productDescription = it) },
                onProductPriceChange = {
                    val formatter = DecimalFormat("#.##")

                    val convertedPrice = try {
                        formatter.format(BigDecimal(it))
                    } catch (e: IllegalArgumentException) {
                        if (it.isEmpty())
                            it
                        else
                            null
                    }

                    convertedPrice?.let {
                        _uiState.value = _uiState.value.copy(productPrice = convertedPrice)
                    }
               },
            )
        }
    }

    fun saveProduct() {
        _uiState.value.run {
            val convertedPrice = try {
                BigDecimal(productPrice)
            } catch(e: IllegalArgumentException) {
                BigDecimal.ZERO
            }

            val product = Product(
                name = productName,
                description = productDescription,
                image = productImageUrl,
                price = convertedPrice
            )

            viewModelScope.launch {
                productDAO.save(product)
            }
        }
    }
}