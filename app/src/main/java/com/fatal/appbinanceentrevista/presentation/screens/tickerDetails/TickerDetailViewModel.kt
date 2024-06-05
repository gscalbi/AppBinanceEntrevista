package com.fatal.appbinanceentrevista.presentation.screens.tickerDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatal.appbinanceentrevista.data.dataSource.local.entity.TickerEntity
import com.fatal.appbinanceentrevista.domain.repository.TickerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerDetailViewModel @Inject constructor(private val repository: TickerRepository) : ViewModel() {

    private val _ticker = MutableStateFlow<TickerEntity?>(null)
    val ticker: StateFlow<TickerEntity?> = _ticker

    fun loadTicker(symbol: String) {
        viewModelScope.launch(Dispatchers.IO){
            repository.getTickerBySymbol(symbol).collect {
                _ticker.value = it
            }
        }
    }
}