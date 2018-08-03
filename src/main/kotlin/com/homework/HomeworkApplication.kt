package com.homework

import com.homework.print.CommandlinePrinter
import com.homework.repo.PersonRepo
import java.io.File
import java.io.IOException

fun main(args: Array<String>) {
    if (args.size < 4) {
        usageAndExit()
    }

    if (args[0] != "cl" && args[0] != "server") {
        usageAndExit()
    }


    val fileDelims = listOf(Pair(args[1], " "), Pair(args[2], ","), Pair(args[3], "|"))

    val personRepo = PersonRepo(fileDelims)

    if(args[0] == "cl") {
        CommandlinePrinter(personRepo).displayOutput()
    }

}

fun usageAndExit() {
    println("java -jar homework.jar (cl | server) /path/to/space-file /path/to/comma-file /path/to/pipe-file")
    System.exit(-1)
}

