package com.homework

import org.junit.*
import org.junit.contrib.java.lang.system.ExpectedSystemExit
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.math.exp

class AppTests {
	private val originalOut : PrintStream = System.out
	private val originalErr : PrintStream = System.err

	private val outStream = ByteArrayOutputStream()
	private val errStream = ByteArrayOutputStream()

	@Rule
	@JvmField
	val expectedSystemExit : ExpectedSystemExit = ExpectedSystemExit.none()

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
	fun test_usagePrintsToStandardErr() {
		App().usage()

		Assert.assertEquals(App.USAGE, errStream.toString())
	}

	@Test
	fun test_wrongNumberOfArgsPrintUsageAndExits() {
		expectedSystemExit.expectSystemExitWithStatus(-1)

		App().run(arrayOf("cl", "", ""))
		Assert.assertEquals(App.USAGE, errStream.toString())
	}

	@Test
	fun test_invalidModeArgumentPrintUsageAndExists() {
		expectedSystemExit.expectSystemExitWithStatus(-1)

		App().run(arrayOf("junk", "", "", ""))
		Assert.assertEquals(App.USAGE, errStream.toString())
	}

	@Test
	fun test_badFilePathsArePrintedToErr() {
		App().run(arrayOf("cl", "/path/to/junk", "/path/to/junk", "/path/to/junk"))
		val expected = "File /path/to/junk does not exist, skipping it\n" +
				"File /path/to/junk does not exist, skipping it\n" +
				"File /path/to/junk does not exist, skipping it\n"

		Assert.assertEquals(expected, errStream.toString())
	}

}
