package com.example.testmvvm

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class NoteAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    private var aoNotes : List<Note> = ArrayList()

    override fun onCreateViewHolder(parent_P: ViewGroup, viewType_P: Int): NoteViewHolder {
        // méthode appelé à chaque création de ViewHolder (élément répété)
        val itemView = LayoutInflater.from(parent_P.context)
            .inflate(R.layout.note_item, parent_P,false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return aoNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, nPosition_P: Int) {
        // méthode permettant d'associer les datas
        val oCurrentNote = aoNotes.get(nPosition_P)
        holder.textViewTitle.text = oCurrentNote.sTitle
        holder.textViewDescription.text = oCurrentNote.sDescription
        holder.textViewPriority.text = oCurrentNote.nPriority.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNotes(aoNotes_P : List<Note>) {
        this.aoNotes = aoNotes_P
        notifyDataSetChanged() // Affiche le REcyclerView
        // Extrait de la doc suite Warning :
        // If you need to change the specific item, then it's better to use notifyItemChanged(position) so that it won't refresh & rebind the whole dataset which can impact the performance if the dataset is large.
    }

    // suite : https://www.youtube.com/watch?v=reSPN7mgshI
}