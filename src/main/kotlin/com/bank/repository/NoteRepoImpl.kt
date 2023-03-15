package com.bank.repository

import com.bank.models.*
import com.bank.repository.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class NoteRepoImpl : NoteRepo {
    private fun resultRowToNote(row: ResultRow) = Note(
        id = row[Notes.id].value,
        title = row[Notes.title],
        body = row[Notes.body],
        creationDate = row[Notes.creationDate],
        userId = row[Notes.userId].value,
        isImportant = row[Notes.isImportant]
    )

    override suspend fun allNotes(): List<Note> = dbQuery {
        Notes.selectAll().map(::resultRowToNote)
    }

    override suspend fun note(id: Int): Note? = dbQuery {
        Notes
            .select { Notes.id eq id }
            .map( ::resultRowToNote )
            .singleOrNull()
    }

    override suspend fun addNewNote(map: Map<String, String>, userId: Int): Note? = dbQuery {
        val insertStatement = Notes.insert {
            it[title] = map["title"]!!
            it[body] = map["body"]!!
            it[creationDate] = System.currentTimeMillis()
            it[isImportant] = map["isImportant"]!!.toBoolean()
            it[Notes.userId] = userId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToNote)
    }

    override suspend fun editNote(note: Note): Boolean = dbQuery {
        Notes.update({ Notes.id eq note.id }) {
            it[title] = note.title
            it[body] = note.body
            it[isImportant] = note.isImportant
        } > 0
    }

    override suspend fun deleteNote(id: Int): Boolean = dbQuery {
        Notes.deleteWhere { Notes.id eq id } > 0
    }

    override suspend fun getNoteByUser(user: User): List<Note> = dbQuery {
        Notes.select { Notes.userId eq user.id }.map(::resultRowToNote)
    }
}

class UserRepoImpl: UserRepo {

    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id].value,
        email = row[Users.email],
        password = row[Users.password],
        userName = row[Users.userName]
    )

    override suspend fun allUsers(): List<User> = dbQuery {
        Users.selectAll().map( ::resultRowToUser )
    }

    override suspend fun doesUserExist(email: String, password: String): Boolean = dbQuery {
        Users.select { Users.email eq email and ( Users.password eq password ) }.count() == 1L
    }

    override suspend fun addNewUser(map: Map<String, String>): User? = dbQuery {
        val insertedValue = Users.insert {
            it[email] = map["email"]!!
            it[password] = map["password"]!!
            it[userName] = map["userName"]!!
        }
        insertedValue.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }

    override suspend fun getUser(map: Map<String, String>): User? = dbQuery {
        Users.select { Users.email eq map["email"]!! and(Users.password eq map["password"]!!) }.map( ::resultRowToUser ).singleOrNull()
    }

    override suspend fun getUserByEmail(email: String): User? = dbQuery {
        Users.select { Users.email eq email }.map( ::resultRowToUser ).singleOrNull()
    }

    override suspend fun getUserById(id: Int): User? = dbQuery {
        Users.select { Users.id eq id }.map( ::resultRowToUser ).singleOrNull()
    }
}
