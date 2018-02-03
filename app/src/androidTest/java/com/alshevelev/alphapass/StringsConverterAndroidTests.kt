package com.alshevelev.alphapass

import android.support.test.runner.AndroidJUnit4
import com.alshevelev.alphapass.core.utility.stringsConverter.StringsConverter
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

/** */
@RunWith(AndroidJUnit4::class)
class StringsConverterAndroidTests {
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
    fun convertToBase64Test() {
        val source = "Our Miron has a crow on his dick. As a crow sings, the dick gets up."

        val testResult = converter.fromBytes(converter.fromBase64(converter.toBase64(converter.toBytes(source))))

        assertEquals(source, testResult)
    }

    @Test
    fun convertToBase64EmptyTest() {
        val source = ""

        val testResult = converter.fromBytes(converter.fromBase64(converter.toBase64(converter.toBytes(source))))

        assertEquals(source, testResult)
    }
}