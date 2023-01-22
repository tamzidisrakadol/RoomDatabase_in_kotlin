package com.example.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "contactTable")
data class Contact(

    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name:String,
    var phone:String,
    val createDate: Date,

    // migrating
    val isActive:Boolean

)