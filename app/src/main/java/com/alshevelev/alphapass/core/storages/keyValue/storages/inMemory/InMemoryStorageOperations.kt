package com.alshevelev.alphapass.core.storages.keyValue.storages.inMemory

import android.util.Base64
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageCommitOperationsInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageReadOperationsInterface

/** */
class InMemoryStorageOperations(private val storage: MutableMap<String, Any>):
    StorageReadOperationsInterface,
    StorageCommitOperationsInterface {

    //region StorageReadOperationsInterface members
    /** Check is item exists  */
    override fun contains(key: String): Boolean = storage.containsKey(key)

    /** Read boolean value  */
    override fun readBoolean(key: String): Boolean? = storage[key]?.let{ it as Boolean}

    /** Checks equality of [valueToCompare] and a stored Boolean value  */
    override fun areEqualsBoolean(key: String, valueToCompare: Boolean): Boolean = readBoolean(key) == valueToCompare

    /** Read string value  */
    override fun readString(key: String): String? = storage[key]?.let{ it as String}

    /** Checks equality of [valueToCompare] and a stored String value  */
    override fun areEqualsString(key: String, valueToCompare: String): Boolean = readString(key) == valueToCompare

    /** Read float value  */
    override fun readFloat(key: String): Float? = storage[key]?.let{ it as Float}

    /** Checks equality of [valueToCompare] and a stored Float value  */
    override fun areEqualsFloat(key: String, valueToCompare: Float): Boolean = readFloat(key) == valueToCompare

    /** Read int value  */
    override fun readInt(key: String): Int? = storage[key]?.let{ it as Int}

    /** Checks equality of [valueToCompare] and a stored Int value  */
    override fun areEqualsInt(key: String, valueToCompare: Int): Boolean = readInt(key) == valueToCompare

    /** Read long value  */
    override fun readLong(key: String): Long? = storage[key]?.let{ it as Long}

    /** Checks equality of [valueToCompare] and a stored long value  */
    override fun areEqualsLong(key: String, valueToCompare: Long): Boolean = readLong(key) == valueToCompare
    //endregion

    //region StorageCommitOperationsInterface members
    /** Complete editing  */
    override fun commit() {
        // do nothing
    }

    /** Put boolean value  */
    override fun putBoolean(key: String, value: Boolean) {
        storage.put(key, value)
    }

    /** Put string value  */
    override fun putString(key: String, value: String) {
        storage.put(key, value)
    }

    /** Put float value  */
    override fun putFloat(key: String, value: Float) {
        storage.put(key, value)
    }

    /** Put int value  */
    override fun putInt(key: String, value: Int) {
        storage.put(key, value)
    }

    /** Put long value  */
    override fun putLong(key: String, value: Long) {
        storage.put(key, value)
    }

    /** Remove value by key  */
    override fun remove(key: String) {
        storage.remove(key)
    }

    /** Remove all values  */
    override fun removeAll() = storage.clear()
    //endregion
}