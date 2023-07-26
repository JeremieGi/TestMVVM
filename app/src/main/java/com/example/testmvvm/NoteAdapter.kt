package com.example.testmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class NoteAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    private var aoNotes : List<Note> = emptyList()

    override fun onCreateViewHolder(parent_P: ViewGroup, viewType_P: Int): NoteViewHolder {
        var itemView = LayoutInflater.from(parent_P.context)
            .inflate(R.layout.note_item, parent_P,false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return aoNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, nPosition_P: Int) {
        var oCurrentNote = aoNotes.get(nPosition_P)
        holder.textViewTitle.text = oCurrentNote.sTitle
        holder.textViewDescription.text = oCurrentNote.sDescription
        holder.textViewPriority.text = oCurrentNote.nPriority.toString()
    }

    fun setNotes(aoNotes_P : List<Note>) {
        this.aoNotes = aoNotes_P
        notifyDataSetChanged()
    }

    // suite : https://www.youtube.com/watch?v=reSPN7mgshI
}