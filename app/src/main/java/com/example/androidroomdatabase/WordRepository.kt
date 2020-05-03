package com.example.androidroomdatabase

import androidx.lifecycle.LiveData


class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    fun insert(word: Word) {
        wordDao.insert(word)
    }

    fun delete(word: Word) {
        wordDao.deleteWord(word)
    }

    fun deleteAll() {
        wordDao.deleteAll()
    }

}