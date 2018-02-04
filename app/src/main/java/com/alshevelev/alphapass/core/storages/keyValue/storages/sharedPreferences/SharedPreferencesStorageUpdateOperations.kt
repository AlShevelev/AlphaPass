package com.alshevelev.alphapass.core.storages.keyValue.storages.sharedPreferences

import android.content.Context
import android.util.Base64
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageCommitOperationsInterface

/** */
class SharedPreferencesStorageUpdateOperations(context: Context, name: String): StorageCommitOperationsInterface {
    private val preferencesEditor = context.getSharedPreferences(name, Context.MODE_PRIVATE).edit()

    /** Put boolean value  */
    override fun putBoolean(key: String, value: Boolean) {
        preferencesEditor.putBoolean(key, value)
    }

    /** Put string value  */
    override fun putString(key: String, value: String) {
        preferencesEditor.putString(key, value)
    }

    /** Put float value  */
    override fun putFloat(key: String, value: Float) {
        preferencesEditor.putFloat(key, value)
    }

    /** Put int value  */
    override fun putInt(key: String, value: Int) {
        preferencesEditor.putInt(key, value)
    }

    /** Put long value  */
    override fun putLong(key: String, value: Long) {
        preferencesEditor.putLong(key, value)
    }
    //endregion

    /** Complete editing  */
    override fun commit() {
        preferencesEditor.apply()
    }

    /** Remove value by key  */
    override fun remove(key: String) {
        preferencesEditor.remove(key)
    }

    /** Remove all values  */
    override fun removeAll() {
        preferencesEditor.clear()
    }
}