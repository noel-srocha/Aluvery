package dev.noelsrocha.aluvery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(searchText: String, onSearchChange: (String) -> Unit) {
    OutlinedTextField(
        label = { Text(text = "Produto") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Ícone de Pesquisa") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        placeholder = { Text(text = "O que você procura?") },
        onValueChange = onSearchChange,
        shape = RoundedCornerShape(100),
        value = searchText,
    )
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    var testSearch by remember { mutableStateOf("") }

    SearchTextField(
        searchText = testSearch,
        onSearchChange = { testSearch = it },
    )
}