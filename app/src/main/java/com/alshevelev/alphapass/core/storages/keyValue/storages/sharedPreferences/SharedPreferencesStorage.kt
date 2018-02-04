package com.alshevelev.alphapass.core.storages.keyValue.storages.sharedPreferences

import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageBase
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageCommitOperationsInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageReadOperationsInterface

/** Storage based on shared preferences */
class SharedPreferencesStorage: StorageBase() {
    private val storageName
        get() = "${App.context!!.packageName}.App"

    /** Create proxy for read */
    override fun createReadOperationsInstance(): StorageReadOperationsInterface =
        SharedPreferencesStorageReadOperations(App.context!!, storageName)

    /** Create proxy for read */
    override fun createUpdateOperationsInstance(): StorageCommitOperationsInterface =
        SharedPreferencesStorageUpdateOperations(App.context!!, storageName)
}