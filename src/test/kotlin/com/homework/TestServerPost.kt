package com.homework

import com.homework.repo.PersonRepo
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import org.junit.After
import org.junit.Assert
import org.junit.Test

class TestServerPost {

    @After
    fun tearDown() {
        PersonRepo.reset()
    }

    @Test
    fun test_AddRecordAPICall() {
        val personToAdd = "{" +
                "\"lastName\":\"Sanders\"," +
                "\"firstName\":\"Bernie\"," +
                "\"gender\":\"M\"," +
                "\"favoriteColor\":\"Aquamarine\"," +
                "\"dateOfBirth\":\"1940-01-01\"" +
                "}"
        withTestApplication(Application::homeworkModule) {
            with(handleRequest(HttpMethod.Post, "/records"){
                setBody(personToAdd)
            }) {
                Assert.assertEquals(HttpStatusCode.Created, response.status())
                val peopleByName = PersonRepo.peopleByLastName()
                Assert.assertEquals("Sanders", peopleByName[0].lastName)
            }
        }
    }
}