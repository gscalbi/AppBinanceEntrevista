package com.fatal.appbinanceentrevista.di

import android.content.Context
import androidx.room.Room
import com.fatal.appbinanceentrevista.data.dataSource.local.dao.TickerDao
import com.fatal.appbinanceentrevista.data.dataSource.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideTickerDao(database: AppDatabase): TickerDao {
        return database.tickerDao()
    }


}