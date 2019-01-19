package com.tokoy.tosa.tarakain

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupMain()
    }

    private fun setupMain() {
        Handler().postDelayed({ displayMain() }, 2000L)
    }

    private fun displayMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}