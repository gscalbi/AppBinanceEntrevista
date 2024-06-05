package com.fatal.appbinanceentrevista.worker

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.fatal.appbinanceentrevista.data.dataSource.remote.websocket.BinanceWebSocketService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltWorker
class WebSocketWorker @Inject constructor(
    @ApplicationContext private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val intent = BinanceWebSocketService.newIntent(context)
                context.startForegroundService(intent)
                Result.success()
            } catch (e: Exception) {
                Result.retry()
            }
        }
    }
}