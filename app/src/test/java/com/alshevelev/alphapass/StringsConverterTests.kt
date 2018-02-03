package com.alshevelev.alphapass

import com.alshevelev.alphapass.core.utility.stringsConverter.StringsConverter
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test

/** */
class StringsConverterTests {
    companion object {
        private lateinit var converter: StringsConverter

        @BeforeClass
        @JvmStatic
        fun setUp() {
            converter = StringsConverter()
        }
    }

    /** */
    @Test
    fun convertToBytesTest() {
        val source = "Our Miron has a crow on his dick. As a crow sings, the dick gets up."

        val testResult = converter.fromBytes(converter.toBytes(source))

        assertEquals(source, testResult)
    }

    @Test
    fun convertToBytesEmptyTest() {
        val source = ""

        val testResult = converter.fromBytes(converter.toBytes(source))

        assertEquals(source, testResult)
    }
}

// Пусто
