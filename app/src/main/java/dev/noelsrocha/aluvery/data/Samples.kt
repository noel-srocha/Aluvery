package dev.noelsrocha.aluvery.data

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import dev.noelsrocha.aluvery.models.Product
import java.math.BigDecimal

val sampleCandies = listOf(
    Product(
        name = "Chocolate",
        price = BigDecimal("3.99"),
        image = "https://images.pexels.com/photos/65882/chocolate-dark-coffee-confiserie-65882.jpeg",
        description = LoremIpsum(50).values.first()
    ),
    Product(
        name = "Ice Cream",
        price = BigDecimal("5.99"),
        image = "https://images.pexels.com/photos/1352278/pexels-photo-1352278.jpeg",
        description = LoremIpsum(50).values.first()
    ),
    Product(
        name = "Cake",
        price = BigDecimal("11.99"),
        image = "https://images.pexels.com/photos/291528/pexels-photo-291528.jpeg",
        description = LoremIpsum(50).values.first()
    )
)

val sampleDrinks = listOf(
    Product(
        name = "Beer",
        price = BigDecimal("5.99"),
        image = "https://images.pexels.com/photos/1552630/pexels-photo-1552630.jpeg",
        description = LoremIpsum(50).values.first()
    ),
    Product(
        name = "Soda",
        price = BigDecimal("4.99"),
        image = "https://images.pexels.com/photos/2775860/pexels-photo-2775860.jpeg",
        description = LoremIpsum(50).values.first()
    ),
    Product(
        name = "Juice",
        price = BigDecimal("7.99"),
        image = "https://images.pexels.com/photos/96974/pexels-photo-96974.jpeg",
        description = LoremIpsum(50).values.first()
    ),
    Product(
        name = "Water",
        price = BigDecimal("2.99"),
        image = "https://images.pexels.com/photos/327090/pexels-photo-327090.jpeg",
        description = LoremIpsum(50).values.first()
    )
)

val sampleProducts: List<Product> = listOf(
    Product(
        name = "Hamburger",
        price = BigDecimal("12.99"),
        image = "https://images.pexels.com/photos/1639557/pexels-photo-1639557.jpeg",
        description = LoremIpsum(50).values.first()
    ),
    Product(
        name = "Pizza",
        price = BigDecimal("19.99"),
        image = "https://images.pexels.com/photos/825661/pexels-photo-825661.jpeg",
        description = LoremIpsum(50).values.first()
    ),
    Product(
        name = "French Fries",
        price = BigDecimal("7.99"),
        image = "https://images.pexels.com/photos/1583884/pexels-photo-1583884.jpeg",
        description = LoremIpsum(50).values.first()
    ), *sampleDrinks.toTypedArray(), *sampleCandies.toTypedArray()
)

val sampleSections = mapOf(
    "On Sale" to sampleProducts,
    "Candies" to sampleCandies,
    "Drinks" to sampleDrinks
)