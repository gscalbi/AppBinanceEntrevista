package com.fatal.appbinanceentrevista.data.dataSource.remote.websocket

import android.util.Log
import com.fatal.appbinanceentrevista.data.dataSource.local.entity.TickerEntity
import com.fatal.appbinanceentrevista.data.mapper.toTickerEntity
import com.fatal.appbinanceentrevista.domain.model.TickerUpdate
import com.google.gson.Gson
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import okhttp3.*
import java.net.URI

class BinanceWebSocketClient(
    private val serverUri: URI
) : WebSocketListener() {

    private val client = OkHttpClient()
    private lateinit var webSocket: WebSocket
    private val _tickerUpdates = Channel<List<TickerEntity>>(Channel.BUFFERED)
    val tickerUpdates = _tickerUpdates.receiveAsFlow()

    fun connect() {
        val request = Request.Builder().url(serverUri.toString()).build()
        Log.e("WebSocket", "Connection Started")

        webSocket = client.newWebSocket(request, this)
    }

    fun close() {
        webSocket.close(1000, "Client closed connection")
    }

    private fun reconnect() {
        close()
        connect()
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        val tickerUpdates = Gson().fromJson(text, Array<TickerUpdate>::class.java).map {ticker->
            ticker.toTickerEntity()
        }
        _tickerUpdates.trySend(tickerUpdates).isSuccess
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        t.printStackTrace()
        Log.e("WebSocket", "Connection failed, trying to reconnect")
        reconnect()
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Log.d("WebSocket", "Connection closing: $reason")
        reconnect()
    }
}