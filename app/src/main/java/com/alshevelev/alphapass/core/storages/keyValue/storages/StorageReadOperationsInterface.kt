package com.alshevelev.alphapass.core.storages.keyValue.storages

/** Interface for read operations of a storage */
interface StorageReadOperationsInterface {
    /** Check is item exists  */
    fun contains(key: String): Boolean

    /** Read boolean value  */
    fun readBoolean(key: String): Boolean?

    /** Checks equality of [valueToCompare] and a stored Boolean value  */
    fun areEqualsBoolean(key: String, valueToCompare: Boolean): Boolean

    /** Read string value  */
    fun readString(key: String): String?

    /** Checks equality of [valueToCompare] and a stored String value  */
    fun areEqualsString(key: String, valueToCompare: String): Boolean

    /** Read float value  */
    fun readFloat(key: String): Float?

    /** Checks equality of [valueToCompare] and a stored Float value  */
    fun areEqualsFloat(key: String, valueToCompare: Float): Boolean

    /** Read int value  */
    fun readInt(key: String): Int?

    /** Checks equality of [valueToCompare] and a stored Int value  */
    fun areEqualsInt(key: String, valueToCompare: Int): Boolean

    /** Read long value  */
    fun readLong(key: String): Long?

    /** Checks equality of [valueToCompare] and a stored long value  */
    fun areEqualsLong(key: String, valueToCompare: Long): Boolean
}