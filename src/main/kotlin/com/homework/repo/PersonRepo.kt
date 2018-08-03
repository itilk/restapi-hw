package com.homework.repo

import com.homework.model.Person
import com.homework.parse.FileParser
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.io.File

class PersonRepo constructor(fileDelims: List<Pair<String, String>>) {
    // use co-routines to load all the files concurrently
    private val people : List<Person> = runBlocking {
            fileDelims.map {
                async { FileParser.parseFile(File(it.first), it.second) }
            }.map { it.await() }.flatMap { it }.toMutableList()
        }

    fun peopleByGender() : List<Person> {
        return people.sortedWith(GenderComparator())
    }

    fun peopleByBirthdate() : List<Person> {
        return people.sortedBy { it.dateOfBirth }
    }

    fun peopleByLastName() : List<Person> {
        return people.sortedByDescending { it.lastName }
    }

}