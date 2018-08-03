package com.homework.repo

import com.homework.model.Gender
import com.homework.model.Person

class GenderComparator : Comparator<Person> {
    override fun compare(p1: Person, p2: Person): Int {
        return when {
            p1.gender == Gender.F && p2.gender == Gender.M -> -1
            p1.gender == p2.gender -> p1.lastName.compareTo(p2.lastName)
            else -> 1
        }
    }
}