package com.fatal.appbinanceentrevista.data.dataSource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fatal.appbinanceentrevista.data.dataSource.local.dao.TickerDao
import com.fatal.appbinanceentrevista.data.dataSource.local.entity.TickerEntity

@Database(entities = [TickerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tickerDao(): TickerDao
}