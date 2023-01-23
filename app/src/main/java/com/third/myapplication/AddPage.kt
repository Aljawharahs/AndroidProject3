package com.third.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText

class AddPage : AppCompatActivity() {
    val database = TaskDataBase(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_page)
        val TaskName = findViewById<EditText>(R.id.task)
        val completd = findViewById<CheckBox>(R.id.taskcompleted)
        val notcompletd = findViewById<CheckBox>(R.id.tasknotcompleted)

        if (completd != null) {
            database.insertUser(TaskName.text.toString(), completd.text.toString())
        }else if (notcompletd != null){
            database.insertUser(TaskName.text.toString(), notcompletd.text.toString())
        }

    }
}