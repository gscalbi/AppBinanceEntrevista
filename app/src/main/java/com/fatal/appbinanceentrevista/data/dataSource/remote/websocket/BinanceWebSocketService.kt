package com.fatal.appbinanceentrevista.data.dataSource.remote.websocket

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.fatal.appbinanceentrevista.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BinanceWebSocketService : Service() {

    @Inject
    lateinit var webSocketClient: BinanceWebSocketClient

    private val serviceScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        val notification = createNotification()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        serviceScope.launch {
            webSocketClient.connect()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.launch {
            webSocketClient.close()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                "BinanceWebSocketServiceChannel",
                "Binance WebSocket Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, "BinanceWebSocketServiceChannel")
            .setContentTitle("Binance WebSocket Service")
            .setContentText("Updating tickers...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, BinanceWebSocketService::class.java)
        }
    }
}