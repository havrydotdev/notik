package com.bank.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Note(val id: Int, val title: String, val body: String, val creationDate: Long, val userId: Int, val isImportant: Boolean)

@Serializable
data class User(val id: Int, val email: String, val password: String, val userName: String)

@Serializable
data class JsonError(val text: String, val isError: Boolean)

object Users: IntIdTable() {
    val email = varchar("email", 256).uniqueIndex()
    val password = varchar("password", 128)
    val userName = varchar("userName", 64)
}

object Notes : IntIdTable() {
    val title = varchar("title", 64)
    val body = varchar("body", 1024)
    val creationDate = long("creationDate")
    val isImportant = bool("isImportant")
    val userId = reference("userId", Users.id)
}
