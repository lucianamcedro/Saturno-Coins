package com.example.saturno_coins.presenter.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.saturno_coins.data.repository.CoinRepository
import com.example.saturno_coins.data.service.ClientService.Companion.coinClientService
import com.example.saturno_coins.databinding.ActivityCoinFavoritesBinding
import com.example.saturno_coins.domain.model.CoinItem
import com.example.saturno_coins.presenter.viewmodel.CoinFavoriteViewModel
import com.example.saturno_coins.presenter.viewmodel.CoinFavoriteViewModelFactory

class FavoriteActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinFavoritesBinding.inflate(layoutInflater)
    }

    private val coinFavoriteRepository = CoinRepository(coinClientService)
    private val coinFavoriteFactory = CoinFavoriteViewModelFactory(coinFavoriteRepository)
    private val coinFavoriteViewModel by viewModels<CoinFavoriteViewModel> { coinFavoriteFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        goToCoinList()

        binding.buttonMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun bindFavorites(coin: List<CoinItem>) {
        binding.tvAppName.text = coin[0].name
    }

    private fun goToCoinList() {
        val coin: CoinItem = intent.getSerializableExtra("coin") as CoinItem
        coinFavoriteViewModel.getCoinFromRetrofit(coin.asset_id)
        coinFavoriteViewModel.coinFavorites.observe(this) { coinFavorite ->
            bindFavorites(coinFavorite)
        }
    }
}
