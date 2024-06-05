package com.fatal.appbinanceentrevista.di

import android.content.Context
import com.fatal.appbinanceentrevista.data.dataSource.remote.websocket.BinanceWebSocketClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.net.URI
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWebSocketClient(
    ): BinanceWebSocketClient {
        val serverUri = URI("wss://stream.binance.com:9443/ws/!ticker@arr")
        return BinanceWebSocketClient(serverUri)
    }
}