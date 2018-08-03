package com.homework.parse

import com.homework.model.Gender
import com.homework.model.Person
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.LocalDate

/**
 * Provides a file parser
 */
object FileParser {
    private val dateTimeFormatter = DateTimeFormat.forPattern("M/d/yyyy")
    /**
     * Parse the lines in the specified file using the specified delimiter.
     * The lines in the file should be in the following format, assume delimiter is "|"
     * LastName|FirstName|Gender|FavoriteColor|DateOfBirth
     *
     * @param file The file to parse
     * @param delimiter The delimiter used to split the line into parts
     * @return List<Person> a list of person objects, one person for each line in the file
     */
    fun parseFile(bufferedReader: BufferedReader, delimiter: String) : List<Person> {
        return bufferedReader.useLines {
            it.map {
                parseLine(it, delimiter)
            }.toList()
        }
    }

    /**
     * @param line - line in the format LastName|FirstName|Gender|FavoriteColor|DateOfBirth assuming "|" is delimiter
     * @param delimiter - the delimiter to use to split the line into a List<String>
     */
    fun parseLine(line: String, delimiter: String) : Person {
        val demo = line.split(delimiter)
        return Person(
                demo[0].trim(),
                demo[1].trim(),
                Gender.valueOf(demo[2].trim()),
                demo[3].trim(),
                dateTimeFormatter.parseLocalDate(demo[4].trim()))
    }
}