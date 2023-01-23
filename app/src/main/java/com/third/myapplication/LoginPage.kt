package com.third.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class LoginPage : AppCompatActivity() {
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         var database = UserDatabase(this)
        var db= database.readableDatabase
        setContentView(R.layout.activity_login_page)
        val loginText = findViewById<EditText>(R.id.phone_number)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        loginText.setOnClickListener {
            val phone = loginText.text.toString()
            if (database.getUser(phone) != null){
                Toast.makeText(this, "welcome to our application ", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
            } else
                Toast.makeText(this, "Register please ", Toast.LENGTH_LONG).show()
        }
    }
}