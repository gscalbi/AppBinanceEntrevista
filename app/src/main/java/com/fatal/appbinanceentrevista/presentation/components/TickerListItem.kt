package com.fatal.appbinanceentrevista.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fatal.appbinanceentrevista.data.dataSource.local.entity.TickerEntity

@Composable
fun TickerListItem(ticker: TickerEntity, onTickerClick:(String)->Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onTickerClick(ticker.symbol)
            }
    ) {
        Text(
            text = ticker.symbol,
            color = Color.LightGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Price: ${ticker.currentClosePrice}", color = Color.LightGray)
    }
}