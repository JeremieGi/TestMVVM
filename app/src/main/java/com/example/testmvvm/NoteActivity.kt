package com.example.testmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast

class NoteActivity : AppCompatActivity() {

    // Passage paramètre à l'activity appelante
    companion object{
        const val EXTRA_ID = "com.example.testmvvm.ID"
        const val EXTRA_TITLE = "com.example.testmvvm.TITLE"
        const val EXTRA_DESCRIPTION = "com.example.testmvvm.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.example.testmvvm.EXTRA_PRIORITY"

        //const val EXTRA_MODE_OUVERTURE = "com.example.testmvvm.EXTRA_MODE_OUVERTURE" // Creation OU Modification


    }

    private lateinit var editTextTitle : EditText
    private lateinit var editTextDescription : EditText
    private lateinit var numberPickerPriority : NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        editTextTitle = findViewById(R.id.SAI_Title)
        editTextDescription = findViewById(R.id.SAI_Description)
        numberPickerPriority = findViewById(R.id.PICK_Priority)

        numberPickerPriority.minValue = 1
        numberPickerPriority.maxValue = 10

        this.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        this.title = "Add note"



    }

    override fun onCreateOptionsMenu(menu_P: Menu?): Boolean {
        // https://www.youtube.com/watch?v=RhGMd8SsA14 => 11min50
        this.menuInflater.inflate(R.menu.add_note_menu,menu_P)
        return true
    }

    override fun onOptionsItemSelected(item_P: MenuItem): Boolean {

        when (item_P.itemId) {

            R.id.OPT_Save -> {
                saveNote()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item_P)
            }
        }

    }

    private fun saveNote() {

        val sTitle = editTextTitle.text.toString()
        val sDescription = editTextDescription.text.toString()
        val nPrio = numberPickerPriority.value

        if (sTitle.trim()==""){
            Toast.makeText(this, "Please insert a title",Toast.LENGTH_LONG).show()
            return
        }

        if (sDescription.trim()==""){
            Toast.makeText(this, "Please insert a description",Toast.LENGTH_LONG).show()
            return
        }

        val oIntent = Intent()
        oIntent.putExtra(EXTRA_TITLE,sTitle)
        oIntent.putExtra(EXTRA_DESCRIPTION,sDescription)
        oIntent.putExtra(EXTRA_PRIORITY,nPrio)

        this.setResult(RESULT_OK,oIntent)

        this.finish() // Ferme

    }
}