package com.example.testmvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel : AndroidViewModel{

    private var oRepository : NoteRepository
    private var oLiveData_allNotes : LiveData<List<Note>>

    constructor(application_P : Application) : super(application_P) {
        oRepository = NoteRepository(application_P)
        oLiveData_allNotes = oRepository.getAllNotes()
    }

    fun insert(oNote_P : Note){
        viewModelScope.launch(Dispatchers.IO){
            oRepository.insert(oNote_P)
        }
    }

    fun update(oNote_P : Note){
        viewModelScope.launch(Dispatchers.IO){
            oRepository.update(oNote_P)
        }
    }

    fun delete(oNote_P : Note){
        viewModelScope.launch(Dispatchers.IO){
            oRepository.delete(oNote_P)
        }
    }

    fun deleteAllNotes(){
        viewModelScope.launch(Dispatchers.IO){
            oRepository.deleteAllNotes()
        }
    }

    fun getAllNotes() : LiveData<List<Note>> {
        return oLiveData_allNotes
    }
}