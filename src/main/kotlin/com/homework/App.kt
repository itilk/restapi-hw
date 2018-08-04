package com.homework

import com.homework.print.CommandlinePrinter
import com.homework.repo.PersonRepo
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/**
 *
 */
class App {
    companion object {
        const val USAGE = "java -jar homework.jar (cl | server) /path/to/space-file /path/to/comma-file /path/to/pipe-file"
    }

    fun run(args: Array<String>) {
        if (args.size < 4) {
            usageAndExit()
        }

        if (args[0] != "cl" && args[0] != "server") {
            usageAndExit()
        }


        val fileDelims = mutableListOf<Pair<BufferedReader, String>>()

        val spaceFile = File(args[1])
        if(spaceFile.exists()) {
            fileDelims.add(Pair(BufferedReader(FileReader(spaceFile)), " "))
        } else {
            invalidFile(spaceFile.absolutePath)
        }

        val commaFile = File(args[2])
        if(commaFile.exists()) {
            fileDelims.add(Pair(BufferedReader(FileReader(commaFile)), ","))
        } else {
            invalidFile(commaFile.absolutePath)
        }

        val pipeFile = File(args[3])
        if(pipeFile.exists()) {
            fileDelims.add(Pair(BufferedReader(FileReader(pipeFile)), "|"))
        } else {
            invalidFile(pipeFile.absolutePath)
        }

        if(args[0] == "cl") {
            CommandlinePrinter(PersonRepo(fileDelims)).displayOutput()
        } else {
           val server = Server(PersonRepo(fileDelims))
            server.run()
        }

    }

    fun usage() {
        System.err.print(USAGE)
    }

    fun invalidFile(path: String) {
        System.err.println(String.format("File %s does not exist, skipping it", path))
    }

    fun usageAndExit() {
        usage()
        System.exit(-1)
    }
}