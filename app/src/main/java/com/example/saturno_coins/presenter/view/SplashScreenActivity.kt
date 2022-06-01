package com.example.saturno_coins.presenter.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.saturno_coins.R
import com.example.saturno_coins.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backgroundTxt: TextView = findViewById(R.id.tv_app_name)
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide)
        backgroundTxt.startAnimation(sideAnimation)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}