package com.example.androidroomdatabase

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface WordDao {

    @Insert
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>

    @Delete
    fun deleteWord(word: Word)

    @Update
    fun update(vararg word: Word)

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAllPaged(): DataSource.Factory<Int, Word>


}