package com.homework.print

import com.homework.repo.PersonRepo

class CommandlinePrinter  {
    fun displayOutput() {
        System.out.println("People By Gender:")
        System.out.println("")
        PersonRepo.peopleByGender().forEach { System.out.println(it) }
        System.out.println("")
        System.out.println("People By Date of Birth:")
        System.out.println("")
        PersonRepo.peopleByBirthdate().forEach { System.out.println(it) }
        System.out.println("")
        System.out.println("People By Last Name:")
        System.out.println("")
        PersonRepo.peopleByLastName().forEach { System.out.println(it) }
    }
}