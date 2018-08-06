package com.homework

import com.fasterxml.jackson.datatype.joda.JodaModule
import com.homework.model.Person
import com.homework.repo.PersonRepo
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.text.SimpleDateFormat

class Server {
    fun run() {
        val server = embeddedServer(Netty, port = 8080, module = Application::homeworkModule)
        server.start(wait = true)
    }
}

fun Application.homeworkModule() {
    routing {
        install(ContentNegotiation) {
            jackson {
                registerModule(JodaModule())
                configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                dateFormat = SimpleDateFormat("M/d/yyyy")
            }
        }

        post("/records") {
            val person = call.receive<Person>()
            PersonRepo.addPerson(person)
            call.respond(HttpStatusCode.Created)
        }

        get("/records/gender") {
            call.respond(PersonRepo.peopleByGender())
        }

        get("/records/name") {
            call.respond(PersonRepo.peopleByLastName())
        }

        get("/records/birthdate") {
            call.respond(PersonRepo.peopleByBirthdate())
        }
    }
}