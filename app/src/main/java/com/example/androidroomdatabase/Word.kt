package com.example.androidroomdatabase

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @NonNull
    @ColumnInfo(name = "word")
    var word: String


    constructor(@NonNull word: String) {
        this.word = word
    }

    @Ignore
    constructor(id: Int, @NonNull word: String) {
        this.id = id
        this.word = word
    }

}