package com.alshevelev.alphapass.core.storages.keyValue

import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageInterface
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance

/** Helper class for access to App-level private shared preferences  */
class KeyValueStorageFacade(override val kodein: Kodein): KeyValueStorageFacadeInterface, KodeinAware {

    private val keyValueStorage: StorageInterface = instance()

    private companion object {
        const val PASSWORD_KEY : String = "PASSWORD"
    }

    /** */
    override fun isPasswordExists(): Boolean = keyValueStorage.read { it.contains(PASSWORD_KEY) }

    /** */
    override fun readPassword(): String = keyValueStorage.read { it.readString(PASSWORD_KEY)!! }

    /** */
    override fun writeOrUpdatePassword(password: String) = keyValueStorage.update { it.putString(PASSWORD_KEY, password) }
}