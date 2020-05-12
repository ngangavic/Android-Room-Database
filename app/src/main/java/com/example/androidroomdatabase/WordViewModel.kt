package com.example.androidroomdatabase

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WordRepository
    val allWords: LiveData<List<Word>>
    var wordsDao: WordDao
    val pagedWords:LiveData<PagedList<Word>>

    init {
        wordsDao = WordRoomDatabase.getDatabase(application).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
        pagedWords=repository.getPagedData()
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }

    fun delete(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(word)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun update(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(word)
    }

//    fun getPagedData(): LiveData<PagedList<Word>> {
//        val factory = wordsDao.getAllPaged()
//        return LivePagedListBuilder(factory, 10)
//            .build()
//    }


}