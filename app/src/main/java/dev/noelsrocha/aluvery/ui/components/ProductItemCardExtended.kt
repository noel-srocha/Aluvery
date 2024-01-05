package dev.noelsrocha.aluvery.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.noelsrocha.aluvery.R
import dev.noelsrocha.aluvery.data.sampleProducts
import dev.noelsrocha.aluvery.extensions.toBrazilianCurrency
import dev.noelsrocha.aluvery.models.Product
import dev.noelsrocha.aluvery.ui.theme.AluveryTheme

@Composable
fun ProductItemCardExtended(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp
) {
    // State for expandable description
    var expandDescription by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier
            .fillMaxWidth()
            .heightIn(150.dp),
        elevation = CardDefaults.cardElevation(elevation),
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700)
                )
                Text(
                    text = product.price.toBrazilianCurrency(),
                    fontSize = 18.sp
                )
            }

            // The Box that contains the description which is able to be expanded or collapsed
            Box(
                Modifier
                    .padding(16.dp)
                    .animateContentSize(animationSpec = tween(100))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { expandDescription = !expandDescription }
            ) {

                if (expandDescription)
                    product.description?.let { Text(text = it) }
                else
                    product.description?.let {
                        Text(
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            text = it,
                        )
                    }
            }
        }
    }
}

@Preview
@Composable
private fun ProductItemCardExtendedPreview() {
    AluveryTheme {
        Surface {
            ProductItemCardExtended(product = sampleProducts.first())
        }
    }
}