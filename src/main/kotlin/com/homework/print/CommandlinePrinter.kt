package com.homework.print

import com.homework.repo.PersonRepo

class CommandlinePrinter constructor(val personRepo: PersonRepo) {
    fun displayOutput() {
        System.out.println("People By Gender:")
        System.out.println("")
        personRepo.peopleByGender().forEach { System.out.println(it) }
        System.out.println("")
        System.out.println("People By Date of Birth:")
        System.out.println("")
        personRepo.peopleByBirthdate().forEach { System.out.println(it) }
        System.out.println("")
        System.out.println("People By Last Name:")
        System.out.println("")
        personRepo.peopleByLastName().forEach { System.out.println(it) }
    }
}