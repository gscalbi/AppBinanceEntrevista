package com.fatal.appbinanceentrevista.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fatal.appbinanceentrevista.data.dataSource.local.entity.TickerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TickerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTickers(tickers: List<TickerEntity>)

    @Query("SELECT * FROM tickers WHERE symbol = :symbol LIMIT 1")
    fun getTickerBySymbol(symbol: String): Flow<TickerEntity?>

    @Query("SELECT * FROM tickers ORDER BY symbol ASC")
     fun getAllTickers(): Flow<List<TickerEntity>>
}