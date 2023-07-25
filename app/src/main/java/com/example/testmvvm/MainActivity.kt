package com.example.testmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var oNoteViewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val noteRepository = NoteRepository(application)
//        noteRepository.getAllNotes()


        // Lien avec le ViewModel
        oNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        var oTest = Note(sTitle = "Titre", sDescription = "Desc", nPriority = 1)
        oNoteViewModel.insert(oTest)

        oNoteViewModel.getAllNotes().observe(this, Observer<List<Note>> {

            // A chaque changement des notes

            // update recycler view

            Toast.makeText(this,"test ${it.size}",Toast.LENGTH_LONG).show()
        })




    }
}