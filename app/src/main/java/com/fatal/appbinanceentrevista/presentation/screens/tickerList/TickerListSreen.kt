package com.fatal.appbinanceentrevista.presentation.screens.tickerList


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fatal.appbinanceentrevista.presentation.components.TickerListItem

@Composable
fun TickerListScreen(onTickerClick:(String)->Unit ,viewModel: TickerListViewModel = hiltViewModel()) {
    var searchQuery by remember { mutableStateOf("") }
    val tickers by viewModel.tickerData.collectAsState()
    val filteredTickers = tickers.filter { it.symbol.contains(searchQuery, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar", color = Color.LightGray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.LightGray,
                unfocusedTextColor = Color.LightGray,
                disabledContainerColor = Color.Black,
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Black
            )
        )
        LazyColumn {
            items(filteredTickers) { ticker ->
                TickerListItem(ticker, onTickerClick = onTickerClick)
                HorizontalDivider(color = Color.DarkGray)
            }
        }
    }
}

