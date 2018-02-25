package com.alshevelev.alphapass.core.storages.keyValue

/** Interface for KeyValueStorageFacade  */
interface KeyValueStorageFacadeInterface {

    /** */
    fun isPasswordExists(): Boolean

    /** */
    fun readPassword(): String

    /** */
    fun writeOrUpdatePassword(password: String)
}
