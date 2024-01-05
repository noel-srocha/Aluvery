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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import dev.noelsrocha.aluvery.ui.states.ProductFormUIState
import dev.noelsrocha.aluvery.ui.viewmodels.ProductFormScreenViewModel
import java.math.BigDecimal
import java.text.DecimalFormat

@Composable
fun ProductFormScreen(
    viewModel: ProductFormScreenViewModel,
    onSaveClick: (Product) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    ProductFormScreen(
        state,
        onSaveClick = {
            viewModel.saveProduct()
        }
    )
}

@Composable
fun ProductFormScreen(
    state: ProductFormUIState = ProductFormUIState(),
    onSaveClick: () -> Unit = {}
) {
    val productImageUrl = state.productImageUrl
    val productName = state.productName
    val productDescription = state.productDescription
    val productPrice = state.productPrice

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier)

        Text("Creating Product", fontSize = 24.sp, fontWeight = FontWeight(700))

        val formatter = remember { DecimalFormat("#,##") }

        if (state.isShowPreview) {
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
            label = { Text("Image URL") },
            value = productImageUrl,
            onValueChange = state.onProductImageUrlChange
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            ),
            label = { Text("Name") },
            value = productName,
            onValueChange = state.onProductNameChange
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next,
            ),
            label = { Text("Price") },
            value = productPrice,
            onValueChange = state.onProductPriceChange,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            label = { Text("Description") },
            value = productDescription,
            onValueChange = state.onProductDescriptionChange
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = onSaveClick
        ) {
            Text(text = "Save", fontSize = 18.sp, fontWeight = FontWeight(700))
        }

        Spacer(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun ProductFormScreenPreview() {
    ProductFormScreen(
        state = ProductFormUIState(
            productImageUrl = "Test Url",
            productName = "Name Test",
            productPrice = "123",
            productDescription = "Description Test"
        )
    )
}