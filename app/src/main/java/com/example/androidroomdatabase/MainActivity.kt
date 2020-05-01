package com.example.androidroomdatabase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room


class MainActivity : AppCompatActivity() {
    lateinit var textView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextTitle=findViewById<EditText>(R.id.editTextTitle)
        val editTextList=findViewById<EditText>(R.id.editTextList)
        val buttonSave=findViewById<Button>(R.id.buttonSave)
        val buttonView=findViewById<Button>(R.id.buttonView)
        textView=findViewById<TextView>(R.id.textViewView)

        buttonSave.setOnClickListener {

        }
        buttonView.setOnClickListener {
            editTextTitle.visibility= View.GONE
            editTextList.visibility= View.GONE
            buttonSave.visibility= View.GONE
            buttonView.visibility= View.GONE

        }

    }

    fun display(text:java.lang.StringBuilder){
        runOnUiThread {
            // Stuff that updates the UI
            textView.text=text
        }
    }

}
