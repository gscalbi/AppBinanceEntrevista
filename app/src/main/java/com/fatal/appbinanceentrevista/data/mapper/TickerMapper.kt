package com.fatal.appbinanceentrevista.data.mapper

import com.fatal.appbinanceentrevista.data.dataSource.local.entity.TickerEntity
import com.fatal.appbinanceentrevista.domain.model.TickerUpdate

fun TickerUpdate.toTickerEntity():TickerEntity{
    return TickerEntity(symbol,priceChange, priceChangePercent, previousClosePrice, currentClosePrice, highPrice, lowPrice)
}