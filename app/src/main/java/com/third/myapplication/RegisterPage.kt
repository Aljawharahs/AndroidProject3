package com.third.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class RegisterPage : AppCompatActivity() {
    val database = UserDatabase(this)
    val db = database.readableDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        val button = findViewById<Button>(R.id.button)
        val number = findViewById<EditText>(R.id.phone_number)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        button.setOnClickListener {
          val phone = number.text.toString()
            if(database.getUser(phone) != null){
                Toast.makeText(this, "You already Register go back to login page! ", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Welcome to our application", Toast.LENGTH_LONG).show()
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
            finish()
        }
    }
}