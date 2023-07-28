package com.example.testmvvm

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteViewHolder(itemView_P: View) : RecyclerView.ViewHolder(itemView_P) {

    internal var textViewTitle : TextView
    internal var textViewDescription : TextView
    internal var textViewPriority : TextView

    init {
        this.textViewTitle = itemView_P.findViewById(R.id.SAI_Title)
        this.textViewDescription = itemView_P.findViewById(R.id.SAI_Description)
        this.textViewPriority = itemView_P.findViewById(R.id.SAI_Priority)
    }


}