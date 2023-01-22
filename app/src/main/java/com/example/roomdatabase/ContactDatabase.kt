package com.example.roomdatabase

import android.content.Context
import androidx.room.*

@Database(entities = [Contact::class], version = 1)
@TypeConverters(Converters::class)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    //creating singleton pattern and using synchronized as blocking
    companion object {

        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java, "CONTACTDB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}