package com.example.myweatherapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class MainDatabase : RoomDatabase() {
    companion object {

        private var database: MainDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): MainDatabase {
            synchronized(LOCK) {
                database?.let {
                    return it
                }
                val newDatabase =
                    Room.databaseBuilder(context, MainDatabase::class.java, DB_NAME).build()
                database = newDatabase
                return newDatabase
            }
        }

    }
    abstract fun dao() : MainDao

}