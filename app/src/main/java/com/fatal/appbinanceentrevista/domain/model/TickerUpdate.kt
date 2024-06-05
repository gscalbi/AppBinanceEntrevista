package com.fatal.appbinanceentrevista.domain.model

import com.google.gson.annotations.SerializedName

data class TickerUpdate(
    @SerializedName("s") val symbol: String, // Symbol
    @SerializedName("p") val priceChange: String, // Price change
    @SerializedName("P") val priceChangePercent: String, // Price change percent
    @SerializedName("x") val previousClosePrice: String, // Previous day's close price
    @SerializedName("c") val currentClosePrice: String, // Current day's close price
    @SerializedName("h") val highPrice: String, // High price
    @SerializedName("l") val lowPrice: String, // Low price
)