package com.example.aniwatch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        enableEdgeToEdge()

        supportActionBar?.hide()

        Handler().postDelayed({
            val intentToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentToMainActivity)
            finish()
        }, 3000)
    }
}