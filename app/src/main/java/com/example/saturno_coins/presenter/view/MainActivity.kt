package com.example.saturno_coins.presenter.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.saturno_coins.data.repository.CoinRepository
import com.example.saturno_coins.data.service.ClientService.Companion.coinClientService
import com.example.saturno_coins.databinding.ActivityMainBinding
import com.example.saturno_coins.domain.model.CoinItem
import com.example.saturno_coins.presenter.adapters.CoinItemAdapter
import com.example.saturno_coins.presenter.viewmodel.CoinViewModel
import com.example.saturno_coins.presenter.viewmodel.CoinViewModelFactory

class MainActivity : AppCompatActivity() {
    private val coinListAdapter by lazy {
        CoinItemAdapter(onClickListener = { coin ->
            goToCoinDetails(coin)
        })
    }

    private val coinRepository = CoinRepository(coinClientService)
    private val coinFactory = CoinViewModelFactory(coinRepository)
    private val coinViewModel by viewModels<CoinViewModel> { coinFactory }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.listRecyclerCoin.adapter = coinListAdapter
        coinAndObserve()

        binding.buttonStar.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun coinAndObserve() {
        coinViewModel.getCoinListFromRetrofit()
        coinViewModel.coin.observe(this) { Coin ->
            setupAdapter(Coin)
        }
    }

    private fun setupAdapter(list: List<CoinItem>) {
        coinListAdapter.submitList(list)
    }

    private fun goToCoinDetails(coin: CoinItem) {
        val intent = Intent(this, CoinDetailsActivity::class.java)
        intent.putExtra("coin", coin)
        startActivity(intent)
    }
}
