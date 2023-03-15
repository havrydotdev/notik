package com.bank.routes

import com.bank.models.JsonError
import com.bank.models.Note
import com.bank.models.User
import com.bank.repository.NoteRepo
import com.bank.repository.NoteRepoImpl
import com.bank.repository.UserRepo
import com.bank.repository.UserRepoImpl
import com.google.gson.Gson
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.thymeleaf.*
import io.ktor.server.util.*
import java.util.regex.Pattern

fun Application.configureRouting() {
    routing {
        crudOperations()
        getAuthOperations()
        get("/") {
            val principal = call.sessions.get<User>()
            if (principal !== null) {
                call.respond(ThymeleafContent("index", mapOf("isDev" to true, "user" to principal, "notes" to dao.getNoteByUser(principal))))
            } else {
                call.respond(ThymeleafContent("index", mapOf("isDev" to true)))
            }
        }
    }
}

fun Route.crudOperations() {
    route("/notes") {
        get {
            call.respond(dao.allNotes())
        }

        post {
            val dto = deserializeJson(call.receive())
            val user = call.sessions.get<User>()
            val post = dao.addNewNote(dto, user!!.id)
            call.respond(post!!)
        }

        put("/{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val dto = call.receive<Note>()
            dao.editNote(dto)
            call.respond(dao.note(id)!!)
        }

        delete("/{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            dao.deleteNote(id)
        }
    }
}

fun Route.getAuthOperations() {
    route("/auth") {
        post("/login") {
            val receive = deserializeJson(call.receive())
            val user = userRepo.getUser(receive)
            if (user != null) {
                call.sessions.set(user)
            } else {
                call.respond(JsonError("Login or email you entered is incorrect", true))
            }
        }

        post("/register") {
            val receive = deserializeJson(call.receive())
            if (userRepo.getUserByEmail(receive["email"]!!) == null) {
                if (checkEmail(receive["email"]!!)) {
                    val user = userRepo.addNewUser(receive)
                    call.respond(user!!)
                } else {
                    call.respond(JsonError("This email is incorrect", true))
                }
            } else {
                call.respond( JsonError("This email is already registered", true) )
            }
        }
    }

    get("/logout") {
        call.sessions.clear<User>()
        call.respondRedirect("/")
    }
}

val dao: NoteRepo = NoteRepoImpl()

var EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

fun checkEmail(email: String): Boolean {
    return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
}

val userRepo: UserRepo = UserRepoImpl()

fun deserializeJson(json: String): Map<String, String> = gson.fromJson<Map<String, String>>(json, MutableMap::class.java)


val gson = Gson()