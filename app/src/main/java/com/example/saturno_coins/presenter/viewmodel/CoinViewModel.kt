package com.example.saturno_coins.presenter.viewmodel

import androidx.lifecycle.*
import com.example.saturno_coins.domain.model.CoinItem
import com.example.saturno_coins.data.repository.CoinRepository
import com.example.saturno_coins.data.repository.ICoinRepository
import kotlinx.coroutines.launch
import retrofit2.await

class CoinViewModel(private val coinRepository: ICoinRepository) : ViewModel() {
    private val _coin = MutableLiveData<List<CoinItem>>()
    val coin: LiveData<List<CoinItem>> = _coin

    fun getCoinListFromRetrofit() {
        viewModelScope.launch {
            val coin = coinRepository.getCoins().await()
            _coin.value = coin
        }
    }
}
class CoinViewModelFactory(private val coinRepository: CoinRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoinViewModel(coinRepository) as T
    }
}
