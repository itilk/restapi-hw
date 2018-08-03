package com.homework.model

import org.joda.time.LocalDate
import org.junit.Assert
import org.junit.Test

class TestPerson {
    @Test
    fun test_toString() {
        val birthday = LocalDate.parse("10/2/1977", Person.dateTimeFormatter)
        val p = Person(
                "Tilk",
                "Israel",
                Gender.M,
                "Blue",
                birthday)

        val pStr = p.toString()

        Assert.assertEquals("Tilk", p.lastName)
        Assert.assertEquals("Israel", p.firstName)
        Assert.assertEquals(Gender.M, p.gender)
        Assert.assertEquals("Blue", p.favoriteColor)
        Assert.assertEquals("Blue", p.favoriteColor)
        Assert.assertEquals(birthday, p.dateOfBirth)
        Assert.assertEquals(
                "Tilk    Israel    M    Blue    10/2/1977",
                pStr)
    }
}