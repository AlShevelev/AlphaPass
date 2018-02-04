package com.alshevelev.alphapass.core.storages.keyValue.storages

/** Interface for read operations of a storage */
interface StorageReadOperationsInterface {
    /** Check is item exists  */
    fun contains(key: String): Boolean

    /** Read boolean value  */
    fun readBoolean(key: String): Boolean?

    /** Read string value  */
    fun readString(key: String): String?

    /** Read float value  */
    fun readFloat(key: String): Float?

    /** Read int value  */
    fun readInt(key: String): Int?

    /** Read long value  */
    fun readLong(key: String): Long?
}