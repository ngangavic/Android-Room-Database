package com.example.androidroomdatabase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter(var context: Context) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>()

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.row_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.wordItemView.text = words[position].word
        holder.wordItemView.setOnClickListener {
            val activity = context as MainActivity
            val intent = Intent(context, NewWordActivity::class.java).putExtra(
                "EXTRA_WORD",
                words[position].word
            ).putExtra("EXTRA_ID", words[position].id.toString())
            activity.startActivityForResult(intent, 200)
        }
    }

    fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    fun getWordAtPosition(position: Int): Word {
        return words.get(position)
    }
}