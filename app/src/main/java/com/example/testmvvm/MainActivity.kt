package com.example.testmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    // Suite :
    // Voir pourquoi l'item de la ZR prend toute la taille de la fenêtre'
    // https://www.youtube.com/watch?v=QJUCD32dzHE

    private lateinit var oNoteViewModel : NoteViewModel

    // Variable permettant de gérer la callback suite à la saisie d'une note
    private val oStartForResultAjout = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when(result.resultCode){
                RESULT_OK -> {
                    var sTitreSaisi = result.data?.getStringExtra(NoteActivity.EXTRA_TITLE).toString()
                    var sDescSaisi = result.data?.getStringExtra(NoteActivity.EXTRA_DESCRIPTION).toString()
                    var nPrio = result.data?.getIntExtra(NoteActivity.EXTRA_PRIORITY,1)
                    if (nPrio==null){
                        nPrio = 0
                    }

                    var oNoteAjouter = Note(sTitle = sTitreSaisi, sDescription = sDescSaisi, nPriority = nPrio)
                    oNoteViewModel.insert(oNoteAjouter)

                    Toast.makeText(this,"Note saved",Toast.LENGTH_LONG).show()
                }
                RESULT_CANCELED -> {
                    Toast.makeText(this,"Note cancelled",Toast.LENGTH_LONG).show()
                } else -> {
            } }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val varBTNAjout = findViewById<FloatingActionButton>(R.id.BTN_Add)
        varBTNAjout.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            oStartForResultAjout.launch(intent)
        }

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