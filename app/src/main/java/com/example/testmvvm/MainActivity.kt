package com.example.testmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var oNoteViewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Déclaration du RecyclerView
        val varRecyclerView = findViewById<RecyclerView>(R.id.ZR_Notes)
        varRecyclerView.layoutManager = LinearLayoutManager(this)
        varRecyclerView.setHasFixedSize(true)

        val oAdapter = NoteAdapter()
        varRecyclerView.adapter = oAdapter

        // Lien avec le ViewModel
        oNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

//        // Init des données
//        var oTest = Note(sTitle = "Titre", sDescription = "Desc", nPriority = 1)
//        oNoteViewModel.insert(oTest)


        oNoteViewModel.getAllNotes().observe(this, Observer<List<Note>> {

            // A chaque changement des notes


            oAdapter.setNotes(it)

            Toast.makeText(this,"test ${it.size}",Toast.LENGTH_LONG).show()
        })




    }
}