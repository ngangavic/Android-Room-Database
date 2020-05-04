package com.example.androidroomdatabase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: WordListAdapter
    private lateinit var wordViewModel: WordViewModel
    private val newWordActivityRequestCode = 100
    private val editWordActivityRequestCode = 200
    lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        floatingActionButton = findViewById(R.id.floatingActionButton)
        recyclerViewAdapter = WordListAdapter(this)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let { recyclerViewAdapter.setWords(it) }
        })

        floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

        val helper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val word = recyclerViewAdapter.getWordAtPosition(viewHolder.adapterPosition)
                    Toast.makeText(this@MainActivity, "Deleting " + word.word, Toast.LENGTH_SHORT)
                        .show()
                    wordViewModel.delete(word)
                }

            })

        helper.attachToRecyclerView(recyclerView)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra("EXTRA_REPLY")?.let {
                val word = Word(it)
                wordViewModel.insert(word)
            }
            Toast.makeText(
                applicationContext,
                "Word saved successfully",
                Toast.LENGTH_LONG
            ).show()
        } else if (requestCode == editWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val word = data?.getStringExtra("EXTRA_REPLY")
            val id = data?.getStringExtra("EXTRA_REPLY_ID")
            if (word != null && id != null) {
                wordViewModel.update(Word(id.toInt(), word))
                Toast.makeText(
                    applicationContext,
                    "Update Successful",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Update Failed",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Not saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                wordViewModel.deleteAll()
                Toast.makeText(this, "All words deleted", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
