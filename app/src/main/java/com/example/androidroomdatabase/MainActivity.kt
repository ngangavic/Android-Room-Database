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
            insertData(0,editTextTitle.text.toString(),editTextList.text.toString())
        }
        buttonView.setOnClickListener {
            editTextTitle.visibility= View.GONE
            editTextList.visibility= View.GONE
            buttonSave.visibility= View.GONE
            buttonView.visibility= View.GONE
            viewData()
        }

    }

    fun insertData(id:Int,title:String,content:String){
        Thread {
           val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "todo-list.db"
            ).build()

            var ent = TodoEntity(id,title,content)

            db.todoDao().insertAll(ent)

//            db.todoDao().getAll().forEach {
//                Log.d("DATA TITLE:", it.title)
//                Log.d("DATA CONTENT:", it.content)
//            }

        }.start()
    }

    fun viewData(){
        var text= StringBuilder()
        Thread {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "todo-list.db"
            ).build()

            db.todoDao().getAll().forEach {
//                Log.d("DATA TITLE:", it.title)
//                Log.d("DATA CONTENT:", it.content)
//                resultBuilder.append(user.firstName).append("\n")
//                text="TITLE:"+it.title+"\n"+
//                        "LIST:"+it.content+"\n"
                text.append("TITLE:").append(it.title).append("\n")
                text.append("LIST:").append(it.content).append("\n")
                Log.d("DATA:",text.toString())
                display(text)
            }

        }.start()

    }

    fun display(text:java.lang.StringBuilder){
        runOnUiThread {
            // Stuff that updates the UI
            textView.text=text
        }
    }

}
