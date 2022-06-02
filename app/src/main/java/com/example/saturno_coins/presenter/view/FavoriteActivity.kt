package com.example.saturno_coins.presenter.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saturno_coins.databinding.ActivityCoinFavoritesBinding
import java.text.SimpleDateFormat
import java.util.*

class FavoriteActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinFavoritesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonMain.setOnClickListener {
            val intent = Intent(this, CoinDetailsActivity::class.java)
            startActivity(intent)
        }

        val date = Calendar.getInstance().time
        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt-BR"))
        binding.tvDate.text = dateTimeFormat.format(date)
    }
}
