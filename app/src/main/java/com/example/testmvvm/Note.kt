package com.example.testmvvm

import androidx.room.Entity
//import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName="tbl_Note")
data class Note(

    @PrimaryKey(autoGenerate = true) var id : Int = 0,

    var sTitle : String,

    var sDescription : String,

    var nPriority : Int


) {


    // https://www.youtube.com/watch?v=Jwdty9jQN0E
    // https://www.youtube.com/watch?v=0cg09tlAAQ0

//    @Ignore val testIgnore: String = ""
}