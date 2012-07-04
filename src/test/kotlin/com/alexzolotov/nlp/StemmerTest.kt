package com.alexzolotov.nlp

import java.io.BufferedReader
import kotlin.support.FunctionIterator
import kotlin.test.assertEquals
import org.testng.annotations.AfterClass as after
import org.testng.annotations.BeforeClass as before
import org.testng.annotations.DataProvider as provider
import org.testng.annotations.Test as test

class StemmerTest {
    val stemmer = Stemmer()

    test(dataProvider = "testData") fun run(input: String, expected: String) {
        assertEquals(expected, stemmer.stem(input))
    }

    provider(name = "testData") fun createData(): java.util.Iterator<Array<Any?>> {
        val inputDataReader = resourceReader("/voc.txt")
        val inputData = inputDataReader?.lineIterator()
        val expectedDataReader = resourceReader("/output.txt")
        val expectedData = expectedDataReader?.lineIterator()
        return FunctionIterator({
            if(inputData!!.hasNext) {
                array<Any?>(inputData!!.next(), expectedData!!.next())
            } else {
                inputDataReader!!.close()
                expectedDataReader!!.close()
                null
            }
        });
    }

    private fun resourceReader(resourceName : String) : BufferedReader?  {
        return this.javaClass.getResourceAsStream(resourceName)?.reader("UTF-8")?.buffered()
    }
}