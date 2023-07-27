package com.example.testmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    // Toutes les itéractions avec la base passeront par cet objet
    private lateinit var oNoteViewModel : NoteViewModel

    // Variable permettant de gérer la callback suite à la saisie d'une note
    private val oStartForResultAjout = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when(result.resultCode){
                RESULT_OK -> {
                    val sTitreSaisi = result.data?.getStringExtra(NoteActivity.EXTRA_TITLE).toString()
                    val sDescSaisi = result.data?.getStringExtra(NoteActivity.EXTRA_DESCRIPTION).toString()
                    var nPrio = result.data?.getIntExtra(NoteActivity.EXTRA_PRIORITY,1)
                    if (nPrio==null){
                        nPrio = 0
                    }

                    val oNoteAAjouter = Note(sTitle = sTitreSaisi, sDescription = sDescSaisi, nPriority = nPrio)
                    oNoteViewModel.insert(oNoteAAjouter)

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

        // Bouton flottant "+"
        val varBTNAjout = findViewById<FloatingActionButton>(R.id.BTN_Add)
        varBTNAjout.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            oStartForResultAjout.launch(intent) // Utilisation de la variable (avec définition de la callback)
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

            // Récup de la nouvelle liste de Note dans l'adapter + réaffichage RecyclerView
            oAdapter.setNotes(it)

            Toast.makeText(this,"test ${it.size}",Toast.LENGTH_LONG).show()
        })




    }
}