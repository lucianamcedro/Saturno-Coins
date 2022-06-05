package com.example.saturno_coins.presenter.viewmodel

import androidx.lifecycle.*
import com.example.saturno_coins.data.repository.CoinRepository
import com.example.saturno_coins.data.repository.ICoinRepository
import com.example.saturno_coins.domain.model.CoinItem
import kotlinx.coroutines.launch
import retrofit2.await

class CoinDetailsViewModel(private val coinRepository: ICoinRepository) : ViewModel() {
    private val _coinDetails = MutableLiveData<List<CoinItem>>()
    val coinDetails: LiveData<List<CoinItem>> = _coinDetails

    fun getCoinFromRetrofit(coinId: String) {
        viewModelScope.launch {
            val coinDetails = coinRepository.getDetailsCoin(coinId).await()
            _coinDetails.value = coinDetails
        }
    }
}

class CoinDetailsViewModelFactory(private val coinRepository: CoinRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoinDetailsViewModel(coinRepository) as T
    }
}
