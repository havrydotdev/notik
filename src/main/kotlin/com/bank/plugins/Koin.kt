package com.bank.plugins

import com.bank.models.User
import com.bank.repository.NoteRepo
import com.bank.repository.NoteRepoImpl
import com.bank.repository.UserRepo
import com.bank.repository.UserRepoImpl
import com.google.gson.Gson
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(koinModule)
    }
}

val koinModule = module {
    single<NoteRepo> { NoteRepoImpl() }
    single<UserRepo> { UserRepoImpl() }
    single { Gson() }
}
