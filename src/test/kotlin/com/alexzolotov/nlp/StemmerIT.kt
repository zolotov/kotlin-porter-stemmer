package com.alexzolotov.nlp

import java.io.BufferedReader
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import kotlin.test.assertEquals

class StemmerIT {
    private val stemmer = Stemmer()

    @Test(dataProvider = "testData")
    fun run(input: String, expected: String) {
        assertEquals(expected, stemmer.stem(input))
    }

    @DataProvider(name = "testData", parallel = true)
    fun createData(): Iterator<Array<Any?>> {
        val inputData = resourceReader("/voc.txt").lineSequence()
        val expectedData = resourceReader("/output.txt").lineSequence()
        return inputData.zip(expectedData).map { arrayOf<Any?>(it.first, it.second) }.iterator()
    }

    private fun resourceReader(resourceName: String): BufferedReader {
        return this.javaClass.getResourceAsStream(resourceName)!!.reader().buffered()
    }
}