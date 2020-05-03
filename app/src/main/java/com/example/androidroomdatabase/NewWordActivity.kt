package com.example.androidroomdatabase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewWordActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editText = findViewById(R.id.editText)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editText.text.toString()
                replyIntent.putExtra("EXTRA_REPLY", word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}
