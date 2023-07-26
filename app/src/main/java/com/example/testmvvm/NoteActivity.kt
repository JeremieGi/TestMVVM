package com.example.testmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import android.widget.NumberPicker

class NoteActivity : AppCompatActivity() {

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // https://www.youtube.com/watch?v=RhGMd8SsA14 => 11min50
        return super.onCreateOptionsMenu(menu)
    }
}