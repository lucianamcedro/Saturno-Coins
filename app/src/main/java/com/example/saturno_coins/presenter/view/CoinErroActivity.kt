package com.example.saturno_coins.presenter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saturno_coins.databinding.ActivityErroBinding

class CoinErroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityErroBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
