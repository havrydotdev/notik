package com.bank.repository

import com.bank.models.Note
import com.bank.models.User

interface NoteRepo {
    suspend fun allNotes(): List<Note>
    suspend fun note(id: Int): Note?
    suspend fun addNewNote(map: Map<String, String>, userId: Int): Note?
    suspend fun editNote(note: Note): Boolean
    suspend fun deleteNote(id: Int): Boolean
    suspend fun getNoteByUser(user: User): List<Note>
}

interface UserRepo {
    suspend fun allUsers(): List<User>
    suspend fun doesUserExist(email: String, password: String): Boolean
    suspend fun addNewUser(map: Map<String, String>): User?
    suspend fun getUser(map: Map<String, String>): User?
    suspend fun getUserByEmail(email: String): User?
    suspend fun getUserById(id: Int): User?
}