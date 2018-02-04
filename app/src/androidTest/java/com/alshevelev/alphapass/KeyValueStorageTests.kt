package com.alshevelev.alphapass

import android.support.test.runner.AndroidJUnit4
import com.alshevelev.alphapass.core.dependencyInjection.coreModule
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.combined.CombinedStorage
import com.github.salomonbrys.kodein.Kodein
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Test

/** */
@RunWith(AndroidJUnit4::class)
class KeyValueStorageTests {
    companion object {

        private val kodein: Kodein = Kodein {
            import(coreModule)
        }

        private lateinit var storage: StorageInterface

        @BeforeClass
        @JvmStatic
        fun setUp() {
            storage = CombinedStorage(kodein)
        }
    }

    /** */
    @Test
    fun stringValueTest() {
        val key = "TEST_KEY_STRING"
        val value = "TEST_VALUE_STRING"

        storage.update { it.putString(key, value) }
        val testResult = storage.read { it.readString(key) }

        assertEquals(value, testResult)
    }

    /** */
    @Test
    fun booleanTest() {
        val key = "TEST_KEY_BOOL"
        val value = true

        storage.update { it.putBoolean(key, value) }
        val testResult = storage.read { it.readBoolean(key) }

        assertEquals(value, testResult)
    }

    /** */
    @Test
    fun floatTest() {
        val key = "TEST_KEY_FLOAT"
        val value = 42.42F

        storage.update { it.putFloat(key, value) }
        val testResult = storage.read { it.readFloat(key) }

        assertEquals(value, testResult)
    }

    /** */
    @Test
    fun intTest() {
        val key = "TEST_KEY_INT"
        val value = 42

        storage.update { it.putInt(key, value) }
        val testResult = storage.read { it.readInt(key) }

        assertEquals(value, testResult)
    }

    /** */
    @Test
    fun longTest() {
        val key = "TEST_KEY_LONG"
        val value = 42L

        storage.update { it.putLong(key, value) }
        val testResult = storage.read { it.readLong(key) }

        assertEquals(value, testResult)
    }
}