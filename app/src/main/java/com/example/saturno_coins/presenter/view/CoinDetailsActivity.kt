package com.example.saturno_coins.presenter.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.saturno_coins.data.repository.CoinRepository
import com.example.saturno_coins.data.service.ClientService.Companion.coinClientService
import com.example.saturno_coins.databinding.ActivityCoinDetailsBinding
import com.example.saturno_coins.domain.model.CoinItem
import com.example.saturno_coins.presenter.viewmodel.CoinDetailsViewModel
import com.example.saturno_coins.presenter.viewmodel.CoinDetailsViewModelFactory

class CoinDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinDetailsBinding.inflate(layoutInflater)
    }

    private val coinDetailsRepository = CoinRepository(coinClientService)
    private val coinDetailsFactory = CoinDetailsViewModelFactory(coinDetailsRepository)
    private val coinDetailsViewModel by viewModels<CoinDetailsViewModel> { coinDetailsFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getCoinDetails()

        binding.iconVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.buttonMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.voltarTela.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.buttonStar.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun bindDetails(coin: List<CoinItem>) {
        binding.tituloDetails.text = coin[0].name
        binding.valorCoin.text = coin[0].price_usd.toString()
        binding.valorHora.text = coin[0].volume_1hrs_usd.toString()
        binding.valorMes.text = coin[0].volume_1mth_usd.toString()
        binding.valorAno.text = coin[0].volume_1day_usd.toString()
    }

    private fun getCoinDetails() {
        val coin: CoinItem = intent.getSerializableExtra("coin") as CoinItem
        coinDetailsViewModel.getCoinFromRetrofit(coin.asset_id)
        coinDetailsViewModel.coinDetails.observe(this) { coinDetails ->
            bindDetails(coinDetails)
        }
    }
}
