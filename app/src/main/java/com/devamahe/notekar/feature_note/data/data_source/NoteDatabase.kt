package com.devamahe.notekar.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devamahe.notekar.feature_note.domain.model.Note

@Database(
    entities = [Note::class], // define tables here.
    version = 1 // initially 1
)

abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
       const val NOTES_DATABASE = "notes_db"
    }
}