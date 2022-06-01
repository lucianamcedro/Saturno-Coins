package com.example.saturno_coins.presenter.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saturno_coins.databinding.ActivityCoinFavoritesBinding

class FavoriteActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinFavoritesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        goToCoinList()
    }

    private fun goToCoinList() {
        binding.buttonMain.setOnClickListener {
            val coinDetailsActivity = Intent(this, CoinDetailsActivity::class.java)
            startActivity(coinDetailsActivity)
        }
    }
}
