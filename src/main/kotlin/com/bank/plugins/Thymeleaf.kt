package com.bank.plugins

import io.ktor.server.application.*
import io.ktor.server.thymeleaf.*
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

fun Application.configureThymeleaf() {
    install(Thymeleaf) {
        setTemplateResolver(ClassLoaderTemplateResolver().apply {
            prefix = "static/js/"
            suffix = ".html"
            characterEncoding = "utf-8"
        })
    }
}
