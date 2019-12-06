package com.example.androidroomdatabase

import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM TODO_ITEMS")
    fun getAll(): List<TodoEntity>
//
//    @Query("SELECT * FROM todo_items WHERE title LIKE :title")
//    fun findByTitle(title: String): TodoEntity

    @Insert
    fun insertAll(vararg todo: TodoEntity)

    @Delete
    fun delete(todo: TodoEntity)

    @Update
    fun updateTodo(vararg todos: TodoEntity)
}