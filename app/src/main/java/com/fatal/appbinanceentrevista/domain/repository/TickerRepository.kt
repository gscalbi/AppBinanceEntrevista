package com.fatal.appbinanceentrevista.domain.repository



import androidx.lifecycle.findViewTreeLifecycleOwner
import com.fatal.appbinanceentrevista.data.dataSource.local.dao.TickerDao
import com.fatal.appbinanceentrevista.data.dataSource.local.entity.TickerEntity
import com.fatal.appbinanceentrevista.data.dataSource.remote.websocket.BinanceWebSocketClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URI


@Singleton
class TickerRepository @Inject constructor(
    private val tickerDao: TickerDao,
    private val serverUri: URI
) {
    private val webSocketClient = BinanceWebSocketClient(serverUri)


    val tickers: Flow<List<TickerEntity>> = tickerDao.getAllTickers()

    suspend fun refreshTickers(tickers: List<TickerEntity>) {
        withContext(Dispatchers.IO) {
            tickerDao.insertTickers(tickers)
        }
    }

    fun getTickerBySymbol(symbol: String): Flow<TickerEntity?> {
        return tickerDao.getTickerBySymbol(symbol).flowOn(Dispatchers.IO)
    }

    fun startWebSocket(scope: CoroutineScope) {
        webSocketClient.connect()
        scope.launch(Dispatchers.IO) {
            webSocketClient.tickerUpdates.collect {
                refreshTickers(it)
            }
        }
    }

    fun stopWebSocket() {
        webSocketClient.close()
    }
}