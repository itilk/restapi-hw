package com.homework.print

import com.homework.repo.PersonRepo
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.StringReader

class TestCommandLinePrinter {
    private val originalOut : PrintStream = System.out
    private val originalErr : PrintStream = System.err

    private val outStream = ByteArrayOutputStream()
    private val errStream = ByteArrayOutputStream()

    @Before
    fun swapOutputs() {
        val newOut = PrintStream(outStream)
        val newErr = PrintStream(errStream)

        System.setOut(newOut)
        System.setErr(newErr)
    }

    @After
    fun resetOutputs() {
        outStream.reset()
        errStream.reset()

        System.setOut(originalOut)
        System.setErr(originalErr)
    }

    @Test
    fun test_displayOutput() {
        val spaceReader = BufferedReader(StringReader("Clinton Hillary F Blue 1/1/1950"))
        val commaReader = BufferedReader(StringReader("Trump, Donald,  M,  Red,  2/2/1948"))
        val pipeReader = BufferedReader(StringReader("Merkel | Angela | F | Black | 3/3/1954"))

        PersonRepo.init(listOf(Pair(spaceReader, " "), Pair(commaReader, ","), Pair(pipeReader, "|")))
        CommandlinePrinter().displayOutput()

        val expectedResult =
                "People By Gender:\n" +
                "\n" +
                "Clinton    Hillary    F    Blue    1/1/1950\n" +
                "Merkel    Angela    F    Black    3/3/1954\n" +
                "Trump    Donald    M    Red    2/2/1948\n" +
                "\n" +
                "People By Date of Birth:\n" +
                "\n" +
                "Trump    Donald    M    Red    2/2/1948\n" +
                "Clinton    Hillary    F    Blue    1/1/1950\n" +
                "Merkel    Angela    F    Black    3/3/1954\n" +
                "\n" +
                "People By Last Name:\n" +
                "\n" +
                "Trump    Donald    M    Red    2/2/1948\n" +
                "Merkel    Angela    F    Black    3/3/1954\n" +
                "Clinton    Hillary    F    Blue    1/1/1950\n"

        Assert.assertEquals(expectedResult, outStream.toString())

    }
}