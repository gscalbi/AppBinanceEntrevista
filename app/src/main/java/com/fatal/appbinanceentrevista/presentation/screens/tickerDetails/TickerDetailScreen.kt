package com.fatal.appbinanceentrevista.presentation.screens.tickerDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TickerDetailScreen(symbol: String, viewModel: TickerDetailViewModel = hiltViewModel()) {
    viewModel.loadTicker(symbol)
    val ticker by viewModel.ticker.collectAsState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        ticker?.let {
            Text(
                text = it.symbol,
                color = Color.Cyan,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = it.currentClosePrice,
                color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Precio cierre anterior:", color = Color.Cyan, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it.previousClosePrice, color = Color.LightGray, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Variación:", color = Color.Cyan, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it.priceChange, color = Color.LightGray, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Porcentaje de variación:", color =Color.Cyan, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${it.priceChangePercent}%", color = Color.LightGray, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Precio mas alto:", color = Color.Cyan, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it.highPrice, color = Color.LightGray, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Precio mas bajo:", color = Color.Cyan, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it.lowPrice, color = Color.LightGray, fontSize = 20.sp)
        } ?: run {
            Text(text = "Loading...", color = Color.LightGray)
        }
    }
}