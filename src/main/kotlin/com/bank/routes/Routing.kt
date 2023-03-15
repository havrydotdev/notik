package com.bank.routes

import com.bank.models.JsonError
import com.bank.models.Note
import com.bank.models.User
import com.bank.repository.NoteRepo
import com.bank.repository.NoteRepoImpl
import com.bank.repository.UserRepo
import com.bank.repository.UserRepoImpl
import com.bank.utils.checkEmail
import com.bank.utils.deserializeJson
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.thymeleaf.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {
        val userRepo by inject<UserRepo>()
        val noteRepo by inject<NoteRepo>()

        getAuthOperations(userRepo)
        crudOperations(noteRepo)
        mainRoute(noteRepo)
    }
}

fun Route.mainRoute(noteRepo: NoteRepo) {
    get("/") {
        val principal = call.sessions.get<User>()
        if (principal !== null) {
            call.respond(ThymeleafContent("index", mapOf("isDev" to true, "user" to principal, "notes" to noteRepo.getNoteByUser(principal))))
        } else {
            call.respond(ThymeleafContent("index", mapOf("isDev" to true)))
        }
    }
}

fun Route.crudOperations(noteRepo: NoteRepo) {
    route("/notes") {
        get {
            call.respond(noteRepo.allNotes())
        }

        post {
            val dto = deserializeJson(call.receive())
            val user = call.sessions.get<User>()
            val post = noteRepo.addNewNote(dto, user!!.id)
            call.respond(post!!)
        }

        put("/{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val dto = call.receive<Note>()
            noteRepo.editNote(dto)
            call.respond(noteRepo.note(id)!!)
        }

        delete("/{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            noteRepo.deleteNote(id)
        }
    }
}

fun Route.getAuthOperations(userRepo: UserRepo) {
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