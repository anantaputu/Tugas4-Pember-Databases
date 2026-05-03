package com.example.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.adapter.NoteAdapter
import com.example.app.database.DatabaseHelper
import com.example.app.database.Note

class MainActivity : AppCompatActivity() {

    lateinit var db: DatabaseHelper
    lateinit var recyclerView: RecyclerView
    lateinit var emptyState: TextView
    lateinit var tvCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        emptyState = findViewById(R.id.tvEmpty)
        tvCount = findViewById(R.id.tvCount)

        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, InputActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        val cursor = db.getAllNotes()
        val list = mutableListOf<Note>()

        cursor.use {
            while (it.moveToNext()) {
                list.add(
                    Note(
                        it.getInt(it.getColumnIndexOrThrow(DatabaseHelper.COL_ID)),
                        it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COL_TITLE)),
                        it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COL_DESCRIPTION))
                    )
                )
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NoteAdapter(list)
        emptyState.visibility = if (list.isEmpty()) TextView.VISIBLE else TextView.GONE
        tvCount.text = getString(R.string.note_count, list.size)
    }
}
