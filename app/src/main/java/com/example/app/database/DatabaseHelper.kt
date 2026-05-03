package com.example.app.database

import android.content.*
import android.database.sqlite.*
import android.database.Cursor

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "notes.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NOTES = "notes"

        const val COL_ID = "id"
        const val COL_TITLE = "title"
        const val COL_DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE $TABLE_NOTES(
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_TITLE TEXT NOT NULL,
                $COL_DESCRIPTION TEXT NOT NULL
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
        onCreate(db)
    }

    fun insertNote(title: String, desc: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_TITLE, title)
            put(COL_DESCRIPTION, desc)
        }
        return db.insert(TABLE_NOTES, null, values) > 0
    }

    fun getAllNotes(): Cursor {
        val db = readableDatabase
        return db.query(
            TABLE_NOTES,
            arrayOf(COL_ID, COL_TITLE, COL_DESCRIPTION),
            null,
            null,
            null,
            null,
            "$COL_ID DESC"
        )
    }
}
