package com.example.app.adapter

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.database.Note

class NoteAdapter(private val list: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTitle)
        val desc: TextView = view.findViewById(R.id.tvDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = list[position]
        holder.title.text = note.title
        holder.desc.text = note.description
    }
}
