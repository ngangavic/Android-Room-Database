package com.example.androidroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "todo-list.db"
            ).build()

            var ent = TodoEntity(0, "Home", "Do web development")

            db.todoDao().insertAll(ent)

            db.todoDao().getAll().forEach {
                Log.d("DATA:", it.toString())
            }

        }.start()

    }
}
