package com.example.saturno_coins.presenter.viewmodel

import androidx.lifecycle.*
import com.example.saturno_coins.data.repository.CoinRepository
import com.example.saturno_coins.data.repository.ICoinRepository
import com.example.saturno_coins.domain.model.CoinItem
import kotlinx.coroutines.launch
import retrofit2.await

class CoinViewModel(private val coinRepository: ICoinRepository) : ViewModel() {
    private val _coinList = MutableLiveData<List<CoinItem>>()
    val coinList: LiveData<List<CoinItem>> = _coinList

    fun getCoinListFromRetrofit() {
        viewModelScope.launch {
            val coinList = coinRepository.getCoins().await()
            _coinList.value = coinList

            // Log.e("ERROR", "Erro: Ocorreu um erro ao carregar a sua lista de moedas.")
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
