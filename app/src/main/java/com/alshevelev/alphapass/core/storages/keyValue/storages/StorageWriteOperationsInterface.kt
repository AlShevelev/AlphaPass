package com.alshevelev.alphapass.core.storages.keyValue.storages

/** Interface for write operations of a storage */
interface StorageWriteOperationsInterface {
    /** Put boolean value  */
    fun putBoolean(key: String, value: Boolean)

    /** Put string value  */
    fun putString(key: String, value: String)

    /** Put float value  */
    fun putFloat(key: String, value: Float)

    /** Put int value  */
    fun putInt(key: String, value: Int)

    /** Put long value  */
    fun putLong(key: String, value: Long)

    /** Remove value by key  */
    fun remove(key: String)

    /** Remove all values  */
    fun removeAll()
}