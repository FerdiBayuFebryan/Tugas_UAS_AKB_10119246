package com.example.consumerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        val handler = Handler()
        handler.postDelayed({
            val intent =Intent(this@SplashScreen, FavouriteActivity::class.java)
            startActivity(intent)
            finish()
        },1500)
    }
}