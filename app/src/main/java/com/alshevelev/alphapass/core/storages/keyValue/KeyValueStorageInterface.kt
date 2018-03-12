package com.alshevelev.alphapass.core.storages.keyValue

/** Interface for KeyValueStorage  */
interface KeyValueStorageInterface {

    /** */
    fun isPasswordExists(): Boolean

    /** */
    fun readPassword(): String

    /** */
    fun writeOrUpdatePassword(password: String)
}
