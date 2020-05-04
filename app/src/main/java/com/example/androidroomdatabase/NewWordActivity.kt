package com.example.androidroomdatabase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NewWordActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var buttonSave: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editText = findViewById(R.id.editText)
        buttonSave = findViewById(R.id.buttonSave)
        textView = findViewById(R.id.textView)

        if (intent.extras != null) {
            textView.setText(R.string.edit_word)
            val word = intent.getStringExtra("EXTRA_WORD")
            editText.setText(word)
            editText.requestFocus()
        }

        buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editText.text.toString()
                if (intent.extras != null && intent.extras!!.containsKey("EXTRA_ID")) {
                    val id = intent.getStringExtra("EXTRA_ID")
                    replyIntent.putExtra("EXTRA_REPLY_ID", id)
                }
                replyIntent.putExtra("EXTRA_REPLY", word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}
