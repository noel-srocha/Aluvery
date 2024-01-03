package dev.noelsrocha.aluvery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.noelsrocha.aluvery.data.sampleProducts
import dev.noelsrocha.aluvery.models.Product
import dev.noelsrocha.aluvery.ui.theme.AluveryTheme

@Composable
fun ProductsSection(
    title: String,
    modifier: Modifier = Modifier,
    products: List<Product> = emptyList(),
) {
    Column(modifier) {
        Text(
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            text = title
        )
        LazyRow(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(products) {
                ProductItemCard(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductsSectionPreview() {
    AluveryTheme {
        Surface {
            ProductsSection("Promoções", products = sampleProducts)
        }
    }
}