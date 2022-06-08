package com.example.saturno_coins.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.service.retrofit.data.repository.CoinRepository
import com.service.retrofit.data.repository.ICoinRepository
import com.service.retrofit.domain.model.CoinItem
import kotlinx.coroutines.launch
import retrofit2.await

class CoinViewModel(private val coinRepository: ICoinRepository) : ViewModel() {
    private val _coinList = MutableLiveData<List<CoinItem>>()
    val coinList: LiveData<List<CoinItem>> = _coinList

    fun getCoinListFromRetrofit() {
        viewModelScope.launch {
            try {
                val coinList = coinRepository.getCoins().await()
                _coinList.value = coinList
            } catch (e: Exception) {
                Log.e("ERROR", "Erro: Ocorreu um erro ao carregar a sua lista de moedas.")
            }
        }
    }
}

class CoinViewModelFactory(
    private val coinRepository: CoinRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoinViewModel(coinRepository) as T
    }
}
