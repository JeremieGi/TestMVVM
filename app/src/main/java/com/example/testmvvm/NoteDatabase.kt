package com.example.testmvvm

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDAO

    companion object { // = static

        @Volatile
        private var oInstance : NoteDatabase? = null

        fun getDatabase(oApplication_P : Application): NoteDatabase {
            if (oInstance == null) {
                synchronized(this) {
                    oInstance =
                        Room.databaseBuilder(oApplication_P,NoteDatabase::class.java, "databaseNote")
                            .build()
               }
            }
            return oInstance!!
        }
/*
        operator fun invoke(context: Context)= oInstance ?: synchronized(LOCK){
            oInstance ?: buildDatabase(context).also { oInstance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            NoteDatabase::class.java, "databaseNote")
            .build()
*/

/*
        private val CALLBACK = object : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val oNoteACreer = Note(sTitle = "Titre1", sDescription = "Desc 1", nPriority = 1)
                CoroutineScope(Dispatchers.IO).launch {
                    oInstance?.noteDao()?.insert(oNoteACreer)
                }

            }

        }
*/
    }

}