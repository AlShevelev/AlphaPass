package com.alshevelev.alphapass.core.storages.keyValue.storages.sharedPreferences

import android.content.Context
import android.util.Base64
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageReadOperationsInterface

/** */
class SharedPreferencesStorageReadOperations(context: Context, name: String): StorageReadOperationsInterface {

    private val preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    /** Check is item exists  */
    override fun contains(key: String): Boolean = preferences.contains(key)

    /** Read boolean value  */
    override fun readBoolean(key: String): Boolean? = read(key) { preferences.getBoolean(key, false)}

    /** Checks equality of [valueToCompare] and a stored Boolean value  */
    override fun areEqualsBoolean(key: String, valueToCompare: Boolean): Boolean = readBoolean(key)!! == valueToCompare

    /** Read string value  */
    override fun readString(key: String): String? = read(key) { preferences.getString(key, "")}

    /** Checks equality of [valueToCompare] and a stored String value  */
    override fun areEqualsString(key: String, valueToCompare: String): Boolean  = readString(key)!! == valueToCompare

    /** Read float value  */
    override fun readFloat(key: String): Float? = read(key) { preferences.getFloat(key, 0F)}

    /** Checks equality of [valueToCompare] and a stored Float value  */
    override fun areEqualsFloat(key: String, valueToCompare: Float): Boolean  = readFloat(key)!! == valueToCompare

    /** Read int value  */
    override fun readInt(key: String): Int? = read(key) { preferences.getInt(key, 0)}

    /** Checks equality of [valueToCompare] and a stored Int value  */
    override fun areEqualsInt(key: String, valueToCompare: Int): Boolean = readInt(key)!! == valueToCompare

    /** Read long value  */
    override fun readLong(key: String): Long? = read(key) { preferences.getLong(key, 0L)}

    /** Checks equality of [valueToCompare] and a stored long value  */
    override fun areEqualsLong(key: String, valueToCompare: Long): Boolean  = readLong(key)!! == valueToCompare

    /** */
    private fun <T>read(key: String, readAction: () -> T): T? =
        if(preferences.contains(key)) readAction.invoke() else null
}