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
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import java.io.StringReader

class TestServerGets {

    @Before
    fun setUp() {
        val spaceReader = BufferedReader(StringReader("Clinton Hillary F Blue 1/1/1950"))
        val commaReader = BufferedReader(StringReader("Trump, Donald,  M,  Red,  2/2/1948"))
        val pipeReader = BufferedReader(StringReader("Merkel | Angela | F | Black | 3/3/1954"))

        PersonRepo.init(listOf(Pair(spaceReader, " "), Pair(commaReader, ","), Pair(pipeReader, "|")))

    }

    @After
    fun tearDown() {
        PersonRepo.reset()
    }

    @Test
    fun test_ByGenderAPICall() {
        val result = "[" +
                "{" +
                "\"lastName\":\"Clinton\"," +
                "\"firstName\":\"Hillary\"," +
                "\"gender\":\"F\"," +
                "\"favoriteColor\":\"Blue\"," +
                "\"dateOfBirth\":\"1950-01-01\"" +
                "}," +
                "{" +
                "\"lastName\":\"Merkel\"," +
                "\"firstName\":\"Angela\"," +
                "\"gender\":\"F\"," +
                "\"favoriteColor\":\"Black\"," +
                "\"dateOfBirth\":\"1954-03-03\"" +
                "}," +
                "{" +
                "\"lastName\":\"Trump\"," +
                "\"firstName\":\"Donald\"," +
                "\"gender\":\"M\"," +
                "\"favoriteColor\":\"Red\"," +
                "\"dateOfBirth\":\"1948-02-02\"" +
                "}" +
                "]"
        withTestApplication(Application::homeworkModule) {
            with(handleRequest(HttpMethod.Get, "/records/gender")) {
                Assert.assertEquals(result, response.content)
            }
        }
    }

    @Test
    fun test_ByBirthdateAPICall() {
        val result = "[" +
                "{" +
                "\"lastName\":\"Trump\"," +
                "\"firstName\":\"Donald\"," +
                "\"gender\":\"M\"," +
                "\"favoriteColor\":\"Red\"," +
                "\"dateOfBirth\":\"1948-02-02\"" +
                "}," +
                "{" +
                "\"lastName\":\"Clinton\"," +
                "\"firstName\":\"Hillary\"," +
                "\"gender\":\"F\"," +
                "\"favoriteColor\":\"Blue\"," +
                "\"dateOfBirth\":\"1950-01-01\"" +
                "}," +
                "{" +
                "\"lastName\":\"Merkel\"," +
                "\"firstName\":\"Angela\"," +
                "\"gender\":\"F\"," +
                "\"favoriteColor\":\"Black\"," +
                "\"dateOfBirth\":\"1954-03-03\"" +
                "}" +
                "]"
        withTestApplication(Application::homeworkModule) {
            with(handleRequest(HttpMethod.Get, "/records/birthdate")) {
                Assert.assertEquals(result, response.content)
            }
        }
    }

    @Test
    fun test_ByNameAPICall() {
        val result = "[" +
                "{" +
                "\"lastName\":\"Trump\"," +
                "\"firstName\":\"Donald\"," +
                "\"gender\":\"M\"," +
                "\"favoriteColor\":\"Red\"," +
                "\"dateOfBirth\":\"1948-02-02\"" +
                "}," +
                "{" +
                "\"lastName\":\"Merkel\"," +
                "\"firstName\":\"Angela\"," +
                "\"gender\":\"F\"," +
                "\"favoriteColor\":\"Black\"," +
                "\"dateOfBirth\":\"1954-03-03\"" +
                "}," +
                "{" +
                "\"lastName\":\"Clinton\"," +
                "\"firstName\":\"Hillary\"," +
                "\"gender\":\"F\"," +
                "\"favoriteColor\":\"Blue\"," +
                "\"dateOfBirth\":\"1950-01-01\"" +
                "}" +
                "]"
        withTestApplication(Application::homeworkModule) {
            with(handleRequest(HttpMethod.Get, "/records/name")) {
                Assert.assertEquals(result, response.content)
            }
        }
    }
}