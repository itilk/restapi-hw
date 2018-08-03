package com.homework.model

import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat


data class Person(
        val lastName: String,
        val firstName: String,
        val gender: Gender,
        val favoriteColor: String,
        val dateOfBirth: LocalDate
) {
    companion object {
        private val dateTimeFormatter = DateTimeFormat.forPattern("M/d/yyyy")
    }

    override fun toString() : String {
        return String.format("%s     %s    %s    %s    %s",
                lastName, firstName, gender.toString(), favoriteColor, dateTimeFormatter.print(dateOfBirth))
    }
}