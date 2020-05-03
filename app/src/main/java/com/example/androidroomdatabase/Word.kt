package com.example.androidroomdatabase

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    var word: String

    constructor(@NonNull word: String) {
        this.word = word
    }

}