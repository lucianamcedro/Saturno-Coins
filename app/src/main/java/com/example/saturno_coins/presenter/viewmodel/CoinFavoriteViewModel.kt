package com.example.saturno_coins.presenter.viewmodel

import androidx.lifecycle.*
import com.example.saturno_coins.data.repository.CoinRepository
import com.example.saturno_coins.data.repository.ICoinRepository
import com.example.saturno_coins.domain.model.CoinItem
import kotlinx.coroutines.launch
import retrofit2.await

class CoinFavoriteViewModel(private val coinRepository: ICoinRepository) : ViewModel() {
    private val _coinFavorite = MutableLiveData<List<CoinItem>>()
    val coinFavorites: LiveData<List<CoinItem>> = _coinFavorite

    fun getCoinFromRetrofit(coinId: String) {
        viewModelScope.launch {
            val coinFavorites = coinRepository.getFavoritesCoin().await()
            _coinFavorite.value = coinFavorites
        }
    }
}
class CoinFavoriteViewModelFactory(private val coinRepository: CoinRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoinFavoriteViewModel(coinRepository) as T
    }
}