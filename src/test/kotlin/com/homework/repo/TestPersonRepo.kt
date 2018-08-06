package com.homework.repo

import com.homework.model.Gender
import com.homework.model.Person
import org.joda.time.LocalDate
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import java.io.StringReader

class TestPersonRepo {

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
    fun test_sortbyGender() {
        val byGender = PersonRepo.peopleByGender()

        Assert.assertEquals("Clinton", byGender[0].lastName)
        Assert.assertEquals("Merkel", byGender[1].lastName)
        Assert.assertEquals("Trump", byGender[2].lastName)
    }

    @Test
    fun test_sortbyBirthdate() {
        val byBirthdate = PersonRepo.peopleByBirthdate()

        Assert.assertEquals("Trump", byBirthdate[0].lastName)
        Assert.assertEquals("Clinton", byBirthdate[1].lastName)
        Assert.assertEquals("Merkel", byBirthdate[2].lastName)
    }

    @Test
    fun test_sortbyLastName() {
        val byLastName = PersonRepo.peopleByLastName()

        Assert.assertEquals("Trump", byLastName[0].lastName)
        Assert.assertEquals("Merkel", byLastName[1].lastName)
        Assert.assertEquals("Clinton", byLastName[2].lastName)
    }

    @Test
    fun test_addPerson() {
        Assert.assertEquals(3, PersonRepo.peopleByLastName().size)
        PersonRepo.addPerson(Person("Sanders", "Bernie", Gender.M, "Orange", LocalDate.now()))
        Assert.assertEquals(4, PersonRepo.peopleByLastName().size)
        Assert.assertEquals("Sanders", PersonRepo.peopleByLastName()[1].lastName)
    }
}