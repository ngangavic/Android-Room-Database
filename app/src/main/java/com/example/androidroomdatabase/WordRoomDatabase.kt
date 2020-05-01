package com.example.androidroomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
abstract class WordRoomDatabase: RoomDatabase() {

    abstract val wordDao:WordDao
    private var INSTANCE: WordRoomDatabase? = null

    open fun getDatabase(context: Context): WordRoomDatabase? {
        if (INSTANCE == null) {
            synchronized(WordRoomDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordRoomDatabase::class.java, "word_database"
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }
        return INSTANCE
    }
}