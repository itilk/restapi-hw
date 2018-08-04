package com.homework

import com.beust.klaxon.Klaxon
import com.homework.repo.PersonRepo
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class Server(val personRepo: PersonRepo) {
    fun run() {
        val server = embeddedServer(Netty, port = 8080) {
            routing {
                get("/records/gender") {
                    call.respondText(Klaxon().toJsonString(personRepo.peopleByGender()), ContentType.Application.Json)
                }
            }
        }

        server.start(wait = true)
    }
}