package com.example.testmvvm

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteRepository(oApplication_P : Application){

    // https://www.youtube.com/watch?v=HhmA9S53XV8 => 2min07

    private var oNoteDAO : NoteDAO
    private lateinit var aoAllNotes : LiveData<List<Note>>


    init {
        var oDatabase = NoteDatabase.getDatabase(oApplication_P)
        // oDatabase = NoteDatabase.invoke(oApplication_P)
        this.oNoteDAO = oDatabase.noteDao()

        CoroutineScope(Dispatchers.IO).launch {
            oNoteDAO.getAllNotes()
        }

    }

    public suspend fun insert(oNote_P : Note) {
        oNoteDAO.insert(oNote_P)
    }

    public suspend fun update(oNote_P : Note) {
        oNoteDAO.update(oNote_P)
    }

    public suspend fun delete(oNote_P : Note) {
        oNoteDAO.delete(oNote_P)
    }

    public suspend fun deleteAllNotes() {
        oNoteDAO.deleteAllNotes()
    }

    public fun getAllNotes() : LiveData<List<Note>> {
        aoAllNotes = oNoteDAO.getAllNotes()
        return aoAllNotes
    }



}