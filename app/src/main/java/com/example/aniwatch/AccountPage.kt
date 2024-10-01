package com.example.aniwatch

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aniwatch.databinding.ActivityAccountPageBinding

class AccountPage : AppCompatActivity() {

    private lateinit var bindingAccount: ActivityAccountPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAccount = ActivityAccountPageBinding.inflate(layoutInflater)
        setContentView(bindingAccount.root)



        bindingAccount.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}