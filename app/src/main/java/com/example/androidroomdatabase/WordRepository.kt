package com.example.androidroomdatabase

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList


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

    fun update(word: Word) {
        wordDao.update(word)
    }

    fun getPagedData(): LiveData<PagedList<Word>> {
        val factory = wordDao.getAllPaged()
        return LivePagedListBuilder(factory, 10)
            .build()
    }

}