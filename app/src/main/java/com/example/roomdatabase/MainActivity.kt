package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var database: ContactDatabase
    lateinit var text:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(applicationContext,ContactDatabase::class.java,"CONTACTDB").build()
        text = findViewById(R.id.text)

        CoroutineScope(Dispatchers.Main).launch {
            database.contactDao().insertContact(Contact(0,"Tamzid Israk","+01956821041"))
        }

        text.setOnClickListener {
            database.contactDao().getContact().observe(this, Observer {
                Log.d("Tag",it.toString())
            })
        }

    }
}