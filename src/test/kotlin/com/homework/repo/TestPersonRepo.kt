package com.homework.repo

import org.junit.Assert
import org.junit.Test
import java.io.BufferedReader
import java.io.StringReader

class TestPersonRepo {

    @Test
    fun test_sortbyGender() {
        val spaceReader = BufferedReader(StringReader("Clinton Hillary F Blue 1/1/1950"))
        val commaReader = BufferedReader(StringReader("Trump, Donald,  M,  Red,  2/2/1948"))
        val pipeReader = BufferedReader(StringReader("Merkel | Angela | F | Black | 3/3/1954"))

        val personRepo = PersonRepo(listOf(Pair(spaceReader, " "), Pair(commaReader, ","), Pair(pipeReader, "|")))

        val byGender = personRepo.peopleByGender()

        Assert.assertEquals("Clinton", byGender[0].lastName)
        Assert.assertEquals("Merkel", byGender[1].lastName)
        Assert.assertEquals("Trump", byGender[2].lastName)
    }

    @Test
    fun test_sortbyBirthdate() {
        val spaceReader = BufferedReader(StringReader("Clinton Hillary F Blue 1/1/1950"))
        val commaReader = BufferedReader(StringReader("Trump, Donald,  M,  Red,  2/2/1948"))
        val pipeReader = BufferedReader(StringReader("Merkel | Angela | F | Black | 3/3/1954"))

        val personRepo = PersonRepo(listOf(Pair(spaceReader, " "), Pair(commaReader, ","), Pair(pipeReader, "|")))

        val byBirthdate = personRepo.peopleByBirthdate()

        Assert.assertEquals("Trump", byBirthdate[0].lastName)
        Assert.assertEquals("Clinton", byBirthdate[1].lastName)
        Assert.assertEquals("Merkel", byBirthdate[2].lastName)
    }

    @Test
    fun test_sortbyLastName() {
        val spaceReader = BufferedReader(StringReader("Clinton Hillary F Blue 1/1/1950"))
        val commaReader = BufferedReader(StringReader("Trump, Donald,  M,  Red,  2/2/1948"))
        val pipeReader = BufferedReader(StringReader("Merkel | Angela | F | Black | 3/3/1954"))

        val personRepo = PersonRepo(listOf(Pair(spaceReader, " "), Pair(commaReader, ","), Pair(pipeReader, "|")))

        val byLastName = personRepo.peopleByLastName()

        Assert.assertEquals("Trump", byLastName[0].lastName)
        Assert.assertEquals("Merkel", byLastName[1].lastName)
        Assert.assertEquals("Clinton", byLastName[2].lastName)
    }
}