package com.example.androidroomdatabase

import androidx.recyclerview.widget.DiffUtil

class WordDiffCallback : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.equals(newItem)
    }
}