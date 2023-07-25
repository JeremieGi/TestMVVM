package com.example.testmvvm

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteViewHolder(itemView_P: View) : RecyclerView.ViewHolder(itemView_P) {

    private var textViewTitle : TextView
    private var textViewDescription : TextView
    private var textViewPriority : TextView

    init {
        this.textViewTitle = itemView_P.findViewById(R.id.SAI_Title)
        this.textViewDescription = itemView_P.findViewById(R.id.SAI_Description)
        this.textViewPriority = itemView_P.findViewById(R.id.SAI_Priority)
    }


}