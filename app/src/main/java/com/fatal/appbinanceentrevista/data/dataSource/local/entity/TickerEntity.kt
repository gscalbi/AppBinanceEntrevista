package com.fatal.appbinanceentrevista.data.dataSource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tickers")
data class TickerEntity(
    @PrimaryKey
    val symbol: String, // Symbol
    val priceChange: String, // Price change
    val priceChangePercent: String, // Price change percent
    val previousClosePrice: String, // Previous day's close price
    val currentClosePrice: String, // Current day's close price
    val highPrice: String, // High price
    val lowPrice: String, // Low price
)
