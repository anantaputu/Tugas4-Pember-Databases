package com.example.app

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.app.database.DatabaseHelper

class InputActivity : AppCompatActivity() {

    lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        db = DatabaseHelper(this)

        val title = findViewById<EditText>(R.id.etTitle)
        val desc = findViewById<EditText>(R.id.etDesc)
        val btn = findViewById<Button>(R.id.btnSave)

        btn.setOnClickListener {
            val titleText = title.text.toString().trim()
            val descText = desc.text.toString().trim()

            if (titleText.isEmpty() || descText.isEmpty()) {
                Toast.makeText(this, "Tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                val saved = db.insertNote(titleText, descText)
                if (saved) {
                    Toast.makeText(this, "Data disimpan", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
