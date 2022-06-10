package com.example.saturno_coins.presenter.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.saturno_coins.data.Dao.CoinDaoRepository
import com.example.saturno_coins.databinding.ActivityMainBinding
import com.example.saturno_coins.presenter.adapters.CoinItemAdapter
import com.example.saturno_coins.presenter.viewmodel.CoinViewModel
import com.example.saturno_coins.presenter.viewmodel.CoinViewModelFactory
import com.service.retrofit.data.repository.CoinRepository
import com.service.retrofit.data.service.ClientService.Companion.coinClientService
import com.service.retrofit.domain.model.CoinApiResult
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

    /*  override fun onResponse(
          call: retrofit2.Call<List<CoinItem>>?,
          response: retrofit2.Response<List<CoinItem>>?
      ) {
          if (response?.code() == 400) {
              Toast.makeText(this, "There is something wrong with your request.", Toast.LENGTH_LONG)
                  .show()
              val intent = Intent(this, CoinErrorActivity::class.java)
              startActivity(intent)
          } else if (response?.code() == 401) {
              Toast.makeText(this, "Your APIKey is wrong", Toast.LENGTH_LONG).show()
              val intent = Intent(this, CoinErrorActivity::class.java)
              startActivity(intent)
          } else if (response?.code() == 403) {
              Toast.makeText(
                  this,
                  "Your API key doesnt't have enough privileges to access this resource",
                  Toast.LENGTH_LONG
              ).show()
              val intent = Intent(this, CoinErrorActivity::class.java)
              startActivity(intent)
          } else if (response?.code() == 429) {
              Toast.makeText(this, "You have exceeded your API key rate limits", Toast.LENGTH_LONG)
                  .show()
              val intent = Intent(this, CoinErrorActivity::class.java)
              startActivity(intent)
          } else if (response?.code() == 500) {
              Toast.makeText(
                  this,
                  "You requested specific single item thatwedon't have at this",
                  Toast.LENGTH_LONG
              ).show()
              val intent = Intent(this, CoinErrorActivity::class.java)
              startActivity(intent)
          } else if (response?.body() != null) {
              response.body()
          }
      }
      override fun onFailure(call: retrofit2.Call<List<CoinItem>>?, t: Throwable?) {}
  */

    private fun coinAndObserve() {
        coinViewModel.getCoinListFromRetrofit()
        coinViewModel.coinList.observe(this) { coinApiResult ->
            when (coinApiResult) {
                is CoinApiResult.Success -> {
                    setupAdapter(coinApiResult.data)
                }
                is CoinApiResult.Error400 -> {
                    Toast.makeText(this, "There is something wrong with your request.", Toast.LENGTH_SHORT).show()
                    Log.e("BadRequest", "There is something wrong with your request.")
                    val intent = Intent(this, CoinErrorActivity::class.java)
                    startActivity(intent)
                }
                is CoinApiResult.Error401 -> {
                    Toast.makeText(this, "Your APIKey is wrong", Toast.LENGTH_SHORT).show()
                    Log.e("Unauthorized", "Your APIKey is wrong")
                    val intent = Intent(this, CoinErrorActivity::class.java)
                    startActivity(intent)
                }
                is CoinApiResult.Error403 -> {
                    Toast.makeText(this, "Your API key doesnt't have enough privileges to access this resource", Toast.LENGTH_SHORT).show()
                    Log.e("Forbidden", "Your API key doesnt't have enough privileges to access this resource ")
                    val intent = Intent(this, CoinErrorActivity::class.java)
                    startActivity(intent)
                }
                is CoinApiResult.Error429 -> {
                    Toast.makeText(this, "You have exceeded your API key rate limits", Toast.LENGTH_SHORT).show()
                    Log.e("Too many requests", "You have exceeded your API key rate limits")
                    val intent = Intent(this, CoinErrorActivity::class.java)
                    startActivity(intent)
                }
                is CoinApiResult.Error500 -> {
                    Toast.makeText(this, "You requested specific single item thatwedon't have at this", Toast.LENGTH_SHORT).show()
                    Log.e("No data", "You requested specific single item thatwedon't have at this")
                    val intent = Intent(this, CoinErrorActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun filter(nome: String?) {
        if (nome != null)
            coinViewModel.coinListSearch.value?.filter {
                it.name.lowercase().contains(nome.lowercase())
            }?.let { setupAdapter(it) }
    }

    private fun setupAdapter(list: List<CoinItem>) {
        coinListAdapter.submitList(list)
    }

    private fun goToCoinDetails(coin: CoinItem) {
        val detailsIntent = Intent(this, CoinDetailsActivity::class.java)
        detailsIntent.putExtra("coin", coin)
        startActivity(detailsIntent)
    }
}
