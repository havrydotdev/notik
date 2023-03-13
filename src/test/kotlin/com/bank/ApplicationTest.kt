package com.bank

import com.bank.routes.configureRouting
//import com.bank.entities.ArticleDto
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello world!", bodyAsText())
        }
    }

//    @Test
//    fun testPost() = testApplication {
//        val client = createClient {
//            install(ContentNegotiation) {
//                json()
//            }
//        }
//        val response = client.post("/articles") {
//            contentType(ContentType.Application.Json)
//            setBody(ArticleDto("second", "Hello from second article!"))
//        }
//        assertEquals("New article second was created", response.bodyAsText())
//    }
}