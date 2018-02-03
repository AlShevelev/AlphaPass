package com.alshevelev.alphapass.core.utility.encryption

/** */
interface EncryptorInterface {
    /** */
    fun encryptByInternalKey(data: ByteArray?): ByteArray?

    /** */
    fun decryptByInternalKey(data: ByteArray?): ByteArray?
}