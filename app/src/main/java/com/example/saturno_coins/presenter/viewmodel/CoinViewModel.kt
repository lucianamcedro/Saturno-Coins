package com.example.saturno_coins.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.service.retrofit.data.repository.CoinRepository
import com.service.retrofit.data.repository.ICoinRepository
import com.service.retrofit.data.service.ClientService.Companion.coinClientService
import com.service.retrofit.domain.model.CoinApiResult
import com.service.retrofit.domain.model.CoinItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class CoinViewModel(private val coinRepository: ICoinRepository) : ViewModel() {
    private val _coinList = MutableLiveData<CoinApiResult<Call<List<CoinItem>>>>()
    val coinList: LiveData<CoinApiResult<Call<List<CoinItem>>>> = _coinList

    private val _coinSearch = MutableLiveData<List<CoinItem>>()
    val coinListSearch: LiveData<List<CoinItem>> = _coinSearch

    fun searchbar() {
        viewModelScope.launch {
            val coinListSearch = coinRepository.getCoins().await()
            _coinSearch.value = coinListSearch
        }
    }

    fun getCoinListFromRetrofit() {
        viewModelScope.launch {
            val call: Call<List<CoinItem>> = coinClientService.getBreeds()
            call.enqueue(object : Callback<List<CoinItem>> {
                override fun onResponse(
                    call: Call<List<CoinItem>>,
                    response: Response<List<CoinItem>>,
                ) {
                    _coinList.value = CoinApiResult.Loading()
                    try {
                        if (response.isSuccessful) {
                            val responseCoin = response.body()
                            _coinList.value = responseCoin?.let { CoinApiResult.Success(it) }
                        } else if (response.code() == 400) {
                            _coinList.value = CoinApiResult.Error400(response.code().toString())
                        } else if (response.code() == 401) {
                            _coinList.value = CoinApiResult.Error401(response.code().toString())
                        } else if (response.code() == 403) {
                            _coinList.value = CoinApiResult.Error403(response.code().toString())
                        } else if (response.code() == 429) {
                            _coinList.value = CoinApiResult.Error429(response.code().toString())
                        } else if (response.code() == 500) {
                            _coinList.value = CoinApiResult.Error500(response.code().toString())
                        }
                    } catch (e: Exception) {
                        e.cause
                    }
                }

                override fun onFailure(call: Call<List<CoinItem>>?, t: Throwable?) {}
            })
        }
    }
}
class CoinViewModelFactory(private val coinRepository: CoinRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoinViewModel(coinRepository) as T
    }
}
