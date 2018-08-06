package com.homework.repo

import com.homework.model.Person
import com.homework.parse.FileParser
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.io.BufferedReader

object PersonRepo {
    // use co-routines to load all the files concurrently
    private var people : MutableList<Person> = mutableListOf()

    /**
     * This initializes this Singleton.  It should only be called once at startup
     */
    fun init(fileDelims: List<Pair<BufferedReader, String>>) {
        if(people.isEmpty()) {
            people = runBlocking {
                fileDelims.map {
                    async { FileParser.parseFile(it.first, it.second) }
                }.map { it.await() }.flatMap { it }.toMutableList()
            }
        }
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

    fun addPerson(person: Person) {
        people.add(person)
    }

    fun reset() {
        people = mutableListOf()
    }
}