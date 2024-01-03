package dev.noelsrocha.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.noelsrocha.aluvery.R
import dev.noelsrocha.aluvery.extensions.toBrazilianCurrency
import dev.noelsrocha.aluvery.models.Product
import dev.noelsrocha.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal


@Composable
fun ProductItemCard(product: Product, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            Modifier
                .width(200.dp)
                .height(250.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val imageSize = 100.dp

            Box(
                Modifier
                    .height(imageSize)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary
                            ),
                        )
                    )
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    model = product.image,
                    modifier = Modifier
                        .size(imageSize)
                        .offset(0.dp, imageSize / 2)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter),
                    placeholder = painterResource(id = R.drawable.placeholder)
                )
            }
            Spacer(modifier = Modifier.height(imageSize / 2))
            Column(
                Modifier
                    .padding(16.dp)
            ) {
                Text(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    text = product.name,
                )
                Text(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(top = 8.dp),
                    text = product.price.toBrazilianCurrency()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemCardPreview() {
    AluveryTheme {
        Surface {
            ProductItemCard(
                Product(
                    name = LoremIpsum(50).values.first(),
                    price = BigDecimal("14.99")
                )
            )
        }
    }
}