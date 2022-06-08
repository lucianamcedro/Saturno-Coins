package com.example.saturno_coins.presenter.view

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.saturno_coins.data.Dao.CoinDaoRepository
import com.example.saturno_coins.databinding.ActivityMainBinding
import com.example.saturno_coins.presenter.adapters.CoinItemAdapter
import com.example.saturno_coins.presenter.viewmodel.CoinViewModel
import com.example.saturno_coins.presenter.viewmodel.CoinViewModelFactory
import com.service.retrofit.data.repository.CoinRepository
import com.service.retrofit.data.service.ClientService.Companion.coinClientService
import com.service.retrofit.domain.model.CoinItem
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val coinListAdapter by lazy {
        CoinItemAdapter(onClickListener = { coin ->
            goToCoinDetails(coin)
        })
    }

    private val coinRepository = CoinRepository(coinClientService)
    private val coinFactory = CoinViewModelFactory(coinRepository)
    private val coinViewModel by viewModels<CoinViewModel> { coinFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        CoinDaoRepository.setContext(this)

        binding.listRecyclerCoin.adapter = coinListAdapter

        coinAndObserve()

        binding.buttonStar.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }

        val date = Calendar.getInstance().time
        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt-BR"))
        binding.dataCoinTopBar.text = dateTimeFormat.format(date)

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })
    }

    private fun coinAndObserve() {
        coinViewModel.getCoinListFromRetrofit()
        coinViewModel.coinList.observe(this) { Coin ->
            setupAdapter(Coin)
        }
    }

    private fun setupAdapter(list: List<CoinItem>) {
        coinListAdapter.submitList(list)
    }

    private fun goToCoinDetails(coin: CoinItem) {
        val detailsIntent = Intent(this, CoinDetailsActivity::class.java)
        detailsIntent.putExtra("coin", coin)
        startActivity(detailsIntent)
    }

    private fun filter(nome: String?) {
        if (nome != null)
            coinViewModel.coinList.value?.filter {
                it.name.lowercase().contains(nome.lowercase())
            }?.let { setupAdapter(it) }
    }
}
