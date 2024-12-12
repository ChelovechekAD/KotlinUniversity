package com.example.lab3.activity

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val accountBtn: Button = findViewById(R.id.accountBtn)
        val catalogBtn: Button = findViewById(R.id.catalogBtn)

        accountBtn.setOnClickListener {
            changeActivity(this, UserPreviewActivity::class.java)
        }

        catalogBtn.setOnClickListener {
            changeActivity(this, CatalogActivity::class.java)
        }
    }

    private fun <T> changeActivity(context: Context, clazz: Class<T>) {
        val intent = Intent(context, clazz)
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}