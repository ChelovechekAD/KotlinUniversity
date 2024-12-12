package com.example.lab3.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3.R
import com.example.lab3.service.AuthService

@SuppressLint("CustomSplashScreen")
class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        checkAuthAndRedirect()
    }

    private fun checkAuthAndRedirect() {
        val authService = AuthService(this)

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            val intent = if (authService.checkAuth()) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, AuthActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 2000)
    }
}