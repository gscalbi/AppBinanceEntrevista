package com.fatal.appbinanceentrevista.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.net.URI
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideServerUri(): URI {
        return URI("wss://stream.binance.com:9443/ws/!ticker@arr")
    }


}