package com.fatal.appbinanceentrevista.data.dataSource.local.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.fatal.appbinanceentrevista.data.dataSource.local.dao.TickerDao
import com.fatal.appbinanceentrevista.data.dataSource.local.entity.TickerEntity

@Database(entities = [TickerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tickerDao(): TickerDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "ticker_database"
                ).build().also { instance = it }
            }
        }
    }
}