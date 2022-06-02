package com.example.saturno_coins.presenter.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.saturno_coins.data.repository.CoinRepository
import com.example.saturno_coins.data.service.ClientService.Companion.coinClientService
import com.example.saturno_coins.databinding.ActivityCoinFavoritesBinding
import java.text.SimpleDateFormat
import java.util.*
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

        binding.buttonMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
<<<<<<< HEAD
        getCoinFavorites()
=======

        val date = Calendar.getInstance().time
        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt-BR"))
        binding.tvDate.text = dateTimeFormat.format(date)
>>>>>>> 0b3789093f63c460293a00ad4f8b7b7fe369fed4
    }

    private fun bindFavorites(coin: List<CoinItem>) {
        binding.tvAppName.text = coin[0].name
    }

    private fun getCoinFavorites() {
        coinFavoriteViewModel.coinFavorites.observe(this) { coinFavorites ->
            bindFavorites(coinFavorites)
        }
    }
}
