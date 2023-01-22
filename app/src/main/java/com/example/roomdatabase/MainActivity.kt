package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var database: ContactDatabase
    lateinit var text:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init the database
        database = ContactDatabase.getDatabase(this)

      /*  //checking if there is same instance
        val database2 = ContactDatabase.getDatabase(this)*/


        text = findViewById(R.id.text)

        //running the insert fun of database in coroutine for suspending function
        CoroutineScope(Dispatchers.Main).launch {
            database.contactDao().insertContact(Contact(0,"Tamzid Israk Adol","+019568210421",
                Date(),false
            ))
            database.contactDao().insertContact(Contact(0,"Tamzid  Adol","+068210421",
                Date(),true
            ))
        }

        text.setOnClickListener {
            database.contactDao().getContact().observe(this, Observer {
                Log.d("Tag",it.toString())
            })
        }

    }
}