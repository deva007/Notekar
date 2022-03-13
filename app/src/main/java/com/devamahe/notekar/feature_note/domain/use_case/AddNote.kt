package com.devamahe.notekar.feature_note.domain.use_case

import com.devamahe.notekar.feature_note.domain.model.InValidNoteExceptionClass
import com.devamahe.notekar.feature_note.domain.model.Note
import com.devamahe.notekar.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InValidNoteExceptionClass::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InValidNoteExceptionClass("Title Can't be Empty")
        }
        if (note.content.isBlank()) {
            throw InValidNoteExceptionClass("Content Can't be Empty")
        }
        repository.insertNode(note)
    }
}