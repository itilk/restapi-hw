package com.homework.repo

import com.homework.model.Gender
import com.homework.model.Person
import org.joda.time.LocalDate
import org.junit.Assert
import org.junit.Test

class TestGenderComparator {
    @Test
    fun test_femalesFirst() {
        val female = Person("", "", Gender.F, "", LocalDate.now())
        val male = Person("", "", Gender.M, "", LocalDate.now())

        val result = GenderComparator().compare(female, male)

        //female should come before male to -1 should be returned
        Assert.assertEquals(-1, result)
    }

    @Test
    fun test_genderSortedByLastNameAscending() {
        val female1 = Person("Anderson", "", Gender.F, "", LocalDate.now())
        val female2 = Person("Bessler", "", Gender.F, "", LocalDate.now())

        //Anderson comes before Bessler and A is one position in front of B so result should be -1
        val result = GenderComparator().compare(female1, female2)
        Assert.assertEquals(-1, result)

    }
}