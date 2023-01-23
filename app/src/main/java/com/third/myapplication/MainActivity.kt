package com.third.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val database = UserDatabase(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login = findViewById<Button>(R.id.login)
        val register = findViewById<Button>(R.id.register)
        login.setOnClickListener {
            val intent = Intent(this,LoginPage::class.java)
            startActivity(intent)
            finish()
        }
        register.setOnClickListener {
            val intent = Intent(this,RegisterPage::class.java)
            startActivity(intent)
            finish()
        }
}}