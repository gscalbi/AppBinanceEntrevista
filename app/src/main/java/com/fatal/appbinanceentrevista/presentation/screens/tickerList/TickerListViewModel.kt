package com.fatal.appbinanceentrevista.presentation.screens.tickerList


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatal.appbinanceentrevista.data.dataSource.local.entity.TickerEntity
import com.fatal.appbinanceentrevista.domain.repository.TickerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerListViewModel @Inject constructor(private val repository: TickerRepository) : ViewModel() {

    val tickerData: StateFlow<List<TickerEntity>> = repository.tickers
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
       startWebSocket()
    }

    fun startWebSocket() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.startWebSocket(viewModelScope)
        }
    }

    fun stopWebSocket() {
        viewModelScope.launch {
            repository.stopWebSocket()
        }
    }
}