package com.example.testmvvm

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class NoteAdapter : RecyclerView.Adapter<NoteViewHolder>()  {

    private var aoNotes : List<Note> = ArrayList()

    //private var oOnClickListener: OnClickListener? = null
    var oOnItemClick : ((Note) -> Unit) ?= null



    override fun onCreateViewHolder(parent_P: ViewGroup, viewType_P: Int): NoteViewHolder {
        // méthode appelé à chaque création de ViewHolder (élément répété)
        val itemView = LayoutInflater.from(parent_P.context)
            .inflate(R.layout.note_item, parent_P,false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return aoNotes.size
    }

    // This new ViewHolder should be constructed with
    // a new View that can represent the items
    // of the given type. You can either create a
    // new View manually or inflate it from an XML
    // layout file.
    override fun onBindViewHolder(itemView_P: NoteViewHolder, nPosition_P: Int) {
        // méthode permettant d'associer les datas
        val oCurrentNote = aoNotes[nPosition_P]
        itemView_P.textViewTitle.text = oCurrentNote.sTitle
        itemView_P.textViewDescription.text = oCurrentNote.sDescription
        itemView_P.textViewPriority.text = oCurrentNote.nPriority.toString()

        // add an onclickListener to the item.
        itemView_P.itemView.setOnClickListener {
//            if (oOnClickListener != null) {
//                oOnClickListener!!.onClick(oCurrentNote,nPosition_P)
//            }
            oOnItemClick?.invoke(oCurrentNote)
        }



    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNotes(aoNotes_P : List<Note>) {
        this.aoNotes = aoNotes_P
        notifyDataSetChanged() // Affiche le REcyclerView
        // Extrait de la doc suite Warning :
        // If you need to change the specific item, then it's better to use notifyItemChanged(position) so that it won't refresh & rebind the whole dataset which can impact the performance if the dataset is large.
    }

    fun getNoteAtPosition(nPosition_P: Int) : Note{
        return aoNotes[nPosition_P]
    }

    // A function to bind the onclickListener.
//    fun setOnClickListener(onClickListener_P: OnClickListener) {
//        this.oOnClickListener = onClickListener_P
//    }



}