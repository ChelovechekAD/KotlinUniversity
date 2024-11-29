package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3.activity.AuthActivity
import com.example.lab3.dto.RegistrationRequest
import com.example.lab3.service.AuthService

class MainActivity : AppCompatActivity() {

    private val authService: AuthService = AuthService(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val label: TextView = findViewById(R.id.textView);
        val loginField: EditText = findViewById(R.id.login_field);
        val emailField: EditText = findViewById(R.id.email_field);
        val passwordField: EditText = findViewById(R.id.password_field);
        val phoneField: EditText = findViewById(R.id.phone_field);
        val btn: Button = findViewById(R.id.reg_btn);

        btn.setOnClickListener {
            val login = loginField.text.toString().trim();
            val email = emailField.text.toString().trim();
            val password = passwordField.text.toString().trim();
            val phone = phoneField.text.toString().trim();

            if (login == "" || email == "" || password == "" || phone == "") {
                Toast.makeText(this, "Поля не заполнены!", Toast.LENGTH_SHORT).show();
            } else {
                val request = RegistrationRequest(login, password, email, phone);
                authService.regUser(request);
            }
        }

        val linkToLogin: TextView = findViewById(R.id.switch_page_to_login)

        linkToLogin.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }
}