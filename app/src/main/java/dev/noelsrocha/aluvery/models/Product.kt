package dev.noelsrocha.aluvery.models

import java.math.BigDecimal

data class Product(
    val name: String,
    val description: String? = null,
    val price: BigDecimal,
    val image: String? = null
)