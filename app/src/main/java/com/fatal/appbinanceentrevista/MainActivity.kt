package com.fatal.appbinanceentrevista

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.fatal.appbinanceentrevista.core.utils.NetworkReceiver
import com.fatal.appbinanceentrevista.data.dataSource.remote.websocket.BinanceWebSocketService
import com.fatal.appbinanceentrevista.worker.WebSocketWorker
import com.fatal.appbinanceentrevista.presentation.navigation.MainNavGraph
import com.fatal.appbinanceentrevista.presentation.screens.tickerList.TickerListViewModel
import com.fatal.appbinanceentrevista.presentation.theme.AppBinanceEntrevistaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: TickerListViewModel
    private lateinit var networkReceiver: NetworkReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkReceiver = NetworkReceiver(this)
        networkReceiver.registerNetworkCallback()

        setContent {
            AppBinanceEntrevistaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    val navController = rememberNavController()

                    viewModel = hiltViewModel()

                    MainNavGraph(navController = navController)
                }
            }
        }
        scheduleWebSocketWorker(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        networkReceiver.unregisterNetworkCallback()
        val intent = Intent(this, BinanceWebSocketService::class.java)
        stopService(intent)

    }
}


private fun scheduleWebSocketWorker(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val workRequest = OneTimeWorkRequestBuilder<WebSocketWorker>()
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)
}