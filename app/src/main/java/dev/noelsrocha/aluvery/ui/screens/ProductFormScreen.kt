package dev.noelsrocha.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.noelsrocha.aluvery.R
import dev.noelsrocha.aluvery.models.Product
import java.math.BigDecimal
import java.text.DecimalFormat

@Composable
fun ProductFormScreen(onSaveClick: (product: Product) -> Unit = {}) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier)

        Text("Criando do Produto", fontSize = 24.sp, fontWeight = FontWeight(700))

        var productImageUrl by remember { mutableStateOf("") }
        var productName by remember { mutableStateOf("") }
        var productDescription by remember { mutableStateOf("") }
        var productPrice by remember { mutableStateOf("") }

        val formatter = remember {
            DecimalFormat("#,##")
        }

        if (productImageUrl.isNotBlank()) {
            AsyncImage(
                contentDescription = null,
                contentScale = ContentScale.Crop,
                model = productImageUrl,
                modifier = Modifier
                    .heightIn(100.dp, 200.dp)
                    .fillMaxWidth(),
                placeholder = painterResource(id = R.drawable.placeholder)
            )
        }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            ),
            label = { Text("Url da Imagem") },
            value = productImageUrl,
            onValueChange = { productImageUrl = it }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            ),
            label = { Text("Nome") },
            value = productName,
            onValueChange = { productName = it }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next,
            ),
            label = { Text("Preço") },
            value = productPrice,
            onValueChange = {
                try {
                    productPrice = formatter.format(BigDecimal(it))
                } catch(ex: IllegalArgumentException) {
                    if (it.isBlank())
                        productPrice = it
                }
            },
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            label = { Text("Descrição") },
            value = productDescription,
            onValueChange = { productDescription = it }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = {
                val convertedPrice = try {
                    BigDecimal(productPrice)
                } catch (Exception: NumberFormatException) {
                    BigDecimal.ZERO
                }

                val product = Product(
                    name = productName,
                    description = productDescription,
                    price = convertedPrice,
                    image = productImageUrl
                )

                onSaveClick(product)
            }
        ) {
            Text(text = "Salvar", fontSize = 18.sp, fontWeight = FontWeight(700))
        }

        Spacer(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun ProductFormScreenPreview() {
    ProductFormScreen()
}