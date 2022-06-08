package com.example.saturno_coins.presenter.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.saturno_coins.data.Dao.CoinDaoRepository
import com.example.saturno_coins.databinding.ActivityCoinFavoritesBinding
import com.example.saturno_coins.presenter.adapters.CoinFavoriteAdapter
import com.service.retrofit.domain.model.CoinItem
import java.text.SimpleDateFormat
import java.util.*

class FavoriteActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinFavoritesBinding.inflate(layoutInflater)
    }

    private val coinAdapter by lazy {
        CoinFavoriteAdapter(onClickListener = { coin ->
            getCoinFavorites(coin)
        })
    }

    private val coinDao = CoinDaoRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        CoinDaoRepository.setContext(this)

        binding.buttonMain.setOnClickListener {
            val listIntent = Intent(this, MainActivity::class.java)
            startActivity(listIntent)
        }

        val date = Calendar.getInstance().time
        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt-BR"))
        binding.tvDate.text = dateTimeFormat.format(date)

        listFavoriteActivity()

        binding.listRecyclerFavorites.adapter = coinAdapter

        startView()
    }

    private fun startView() {
        val favoriteRecyclerView = binding.listRecyclerFavorites
        favoriteRecyclerView.adapter = coinAdapter
        favoriteRecyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    fun listFavoriteActivity() {
        val result = coinDao.listFavorite()
        setListAdapter(result)
    }

    private fun setListAdapter(list: List<CoinItem>) {
        coinAdapter.submitList(list)
    }

    private fun getCoinFavorites(coin: CoinItem) {
        val favoriteIntent = Intent(this, CoinDetailsActivity::class.java)
        favoriteIntent.putExtra("coin", coin)
        startActivity(favoriteIntent)
    }
}
