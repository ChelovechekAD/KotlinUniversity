package com.example.lab3.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3.R
import com.example.lab3.service.AuthService
import com.example.lab3.service.UserService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserPreviewActivity : AppCompatActivity() {

    private val userService: UserService = UserService(this);
    private val authService: AuthService = AuthService(this);

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("UserPreviewActivity", "onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_preview)

        GlobalScope.launch(Dispatchers.IO) {
            val user = userService.getUserInformation()
            if (user != null) {
                val login: TextView = findViewById(R.id.loginTextView);
                val phone: TextView = findViewById(R.id.phoneTextView);
                val email: TextView = findViewById(R.id.emailTextView);
                val logoutBtn: TextView = findViewById(R.id.logoutBtn);
                login.text = user.login
                email.text = String.format("Email: %s", user.email)
                phone.text = String.format("Телефон: %s", user.phone)
                logoutBtn.setOnClickListener {
                    authService.logout()
                }
            }
        }
    }
}