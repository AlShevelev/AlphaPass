package com.alshevelev.alphapass

import android.support.test.runner.AndroidJUnit4
import com.alshevelev.alphapass.core.utility.encryption.Encryptor
import com.alshevelev.alphapass.core.utility.stringsConverter.StringsConverter
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.BeforeClass

/** */
@RunWith(AndroidJUnit4::class)
class EncryptorTests {
    companion object {
        private lateinit var encryptor: Encryptor
        private lateinit var converter: StringsConverter

        @BeforeClass
        @JvmStatic
        fun setUp() {
            converter = StringsConverter()
            encryptor = Encryptor()
        }
    }

    @Test
    fun encryptDecryptTest() {
        val sourceText = "Our Miron has a crow on his dick. As a crow sings, the dick gets up."
        val source = converter.toBytes(sourceText)

        val testResult = converter.fromBytes(encryptor.decryptByInternalKey(encryptor.encryptByInternalKey(source))!!)

        assertEquals(sourceText, testResult)
    }

    @Test
    fun encryptDecryptEmptyTest() {
        val sourceText = ""
        val source = converter.toBytes(sourceText)

        val testResult = converter.fromBytes(encryptor.decryptByInternalKey(encryptor.encryptByInternalKey(source))!!)

        assertEquals(sourceText, testResult)
    }

    @Test
    fun encryptDecryptNullTest() {
        val source = null

        val testResult = encryptor.decryptByInternalKey(encryptor.encryptByInternalKey(source))

        assertNull(testResult)
    }
}