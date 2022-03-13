package com.devamahe.notekar.di

import android.app.Application
import androidx.room.Room
import com.devamahe.notekar.feature_note.data.data_source.NoteDatabase
import com.devamahe.notekar.feature_note.data.repository.NoteRepositoryImpl
import com.devamahe.notekar.feature_note.domain.repository.NoteRepository
import com.devamahe.notekar.feature_note.domain.use_case.AddNote
import com.devamahe.notekar.feature_note.domain.use_case.DeleteNote
import com.devamahe.notekar.feature_note.domain.use_case.GetNotes
import com.devamahe.notekar.feature_note.domain.use_case.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.NOTES_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUseCase(noteRepository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            getNotes = GetNotes(repository = noteRepository),
            deleteNote = DeleteNote(repository = noteRepository),
            addNote = AddNote(repository = noteRepository)
        )
    }
}