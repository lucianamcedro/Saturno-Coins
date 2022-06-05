package com.example.saturno_coins.presenter.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.saturno_coins.R
import com.example.saturno_coins.data.Dao.CoinDaoRepository
import com.example.saturno_coins.data.repository.CoinRepository
import com.example.saturno_coins.data.service.ClientService.Companion.coinClientService
import com.example.saturno_coins.databinding.ActivityCoinDetailsBinding
import com.example.saturno_coins.domain.model.CoinItem
import com.example.saturno_coins.presenter.viewmodel.CoinDetailsViewModel
import com.example.saturno_coins.presenter.viewmodel.CoinDetailsViewModelFactory
import java.math.RoundingMode
import java.text.DecimalFormat

class CoinDetailsActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy {
        ActivityCoinDetailsBinding.inflate(layoutInflater)
    }

    private val coinDetailsRepository = CoinRepository(coinClientService)
    private val coinDetailsFactory = CoinDetailsViewModelFactory(coinDetailsRepository)
    private val coinDetailsViewModel by viewModels<CoinDetailsViewModel> { coinDetailsFactory }
    private val coinDao = CoinDaoRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        CoinDaoRepository.setContext(this)

        getCoinDetails()

        binding.iconVoltar.setOnClickListener(this)
        binding.voltarTela.setOnClickListener(this)

        binding.buttonMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonStar.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        getFavorite()
    }

    private fun bindDetails(coin: List<CoinItem>) {

        val decimal = DecimalFormat("$ ###,###.##")
        decimal.roundingMode = RoundingMode.UP

        val valueTotal = decimal.format(coin[0].price_usd)
        val valueHrs = decimal.format(coin[0].volume_1hrs_usd)
        val valueMes = decimal.format(coin[0].volume_1mth_usd)
        val valueDay = decimal.format(coin[0].volume_1day_usd)

        binding.tituloDetails.text = coin[0].name
        binding.valorCoin.text = valueTotal.toString()
        binding.valorHora.text = valueHrs.toString()
        binding.valorMes.text = valueMes.toString()
        binding.valorAno.text = valueDay.toString()

        Glide
            .with(binding.root.context)
            .load(coin[0].getImageCoin())
            .centerCrop()
            .into(binding.imageLogo)
    }

    private fun getCoinDetails() {
        val coin: CoinItem = intent.getSerializableExtra("coin") as CoinItem
        coinDetailsViewModel.getCoinFromRetrofit(coin.asset_id)
        coinDetailsViewModel.coinDetails.observe(this) { coinDetails ->
            bindDetails(coinDetails)
        }
    }

    fun insertCoinTest(coin: CoinItem) {
        binding.buttonAdicionar.setOnClickListener {
            coinDao.addFavorite(coin)
            getFavorite()
            Toast.makeText(this, "Moeda adicionada aos favoritos.", Toast.LENGTH_SHORT).show()
            binding.buttonAdicionar.text = "REMOVER"
        }
    }

    fun delete(coin: CoinItem) {
        binding.buttonAdicionar.text = "REMOVER"
        binding.buttonAdicionar.setOnClickListener {
            coinDao.deleteFavorite(coin)
            Toast.makeText(this, "Moeda removida dos favoritos.", Toast.LENGTH_SHORT).show()
            binding.buttonAdicionar.text = "ADICIONAR"
            getFavorite()
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.icon_voltar || view.id == R.id.voltar_tela) {
            finish()
        }
    }

    fun getFavorite() {
        val coin: CoinItem = intent.getSerializableExtra("coin") as CoinItem
        val favorite = coinDao.loadDatabase(coin.asset_id)
        var visibilidade = View.GONE
        if (favorite != null) {
            visibilidade = View.VISIBLE
            delete(coin)
        } else {
            insertCoinTest(coin)
        }
        binding.ivFavoriteStar.visibility = visibilidade
    }
}
