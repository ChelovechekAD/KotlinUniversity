package com.example.lab3.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab3.MainActivity
import com.example.lab3.R
import com.example.lab3.dto.LoginRequest
import com.example.lab3.dto.RegistrationRequest
import com.example.lab3.service.AuthServiceService

class AuthActivity : AppCompatActivity() {

    private val authServiceService: AuthServiceService = AuthServiceService(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val loginField: EditText = findViewById(R.id.login_field);
        val passwordField: EditText = findViewById(R.id.password_field);
        val btn: Button = findViewById(R.id.login_btn);
        val deleteBtn: Button = findViewById(R.id.delete_by_login_btn);

        btn.setOnClickListener {
            val login = loginField.text.toString().trim();
            val password = passwordField.text.toString().trim();

            if (login == "" || password == "") {
                Toast.makeText(this, "Поля не заполнены!", Toast.LENGTH_SHORT).show();
            } else {
                val request = LoginRequest(login, password);
                authServiceService.loginUser(request);
            }
        }

        deleteBtn.setOnClickListener {
            val login = loginField.text.toString().trim();

            if (login == "") {
                Toast.makeText(this, "Поля не заполнены!", Toast.LENGTH_SHORT).show();
            } else {
                authServiceService.deleteUserByLogin(login)
            }
        }

        val linkToReg: TextView = findViewById(R.id.switch_page_to_reg)

        linkToReg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}