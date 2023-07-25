package com.example.testmvvm

import androidx.lifecycle.LiveData
import androidx.room.*


// NoteDAO fournit les méthodes utilisées par le reste de l'application pour interagir avec les données de la table tbl_Notes.

@Dao
interface NoteDAO {

    // Doc : https://developer.android.com/training/data-storage/room/accessing-data?hl=fr
    // suspend  => asynchrone : Pour empêcher les requêtes de bloquer l'interface utilisateur, Room n'autorise pas l'accès à la base de données sur le thread principal.

    @Insert
    suspend fun insert(oNote_P : Note) : Void

    @Update
    suspend fun update(oNote_P : Note) : Void

    @Delete
    suspend fun delete(oNote_P : Note) : Void

    @Query("DELETE FROM tbl_Note")
    suspend fun deleteAllNotes() : Void

    @Query("SELECT * FROM tbl_Note ORDER BY tbl_Note.nPriority DESC")
    fun getAllNotes() : LiveData<List<Note>> // L'activity est avertie si il y a un changement dans la liste

}
