package com.alshevelev.alphapass.core.storages.db.typeConverters

import android.arch.persistence.room.TypeConverter
import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.core.storages.db.entities.customFields.EncryptedString
import com.alshevelev.alphapass.core.utility.encryption.EncryptorInterface
import com.alshevelev.alphapass.core.utility.stringsConverter.StringsConverterInterface
import com.github.salomonbrys.kodein.instance

@Suppress("MemberVisibilityCanPrivate")
/** */
class CustomTypesConverters {
    /** */
    @TypeConverter
    fun toStringNullable(data: ByteArray?): EncryptedString? = data?.let {
        val stringsConverter: StringsConverterInterface = App.injections!!.instance()
        val encryptor: EncryptorInterface = App.injections!!.instance()

        EncryptedString(stringsConverter.fromBytes(encryptor.decryptByInternalKey(data)!!))
    }

    /** */
    @TypeConverter
    fun fromStringNullable(data: EncryptedString?): ByteArray? = data?.let {
        val stringsConverter: StringsConverterInterface = App.injections!!.instance()
        val encryptor: EncryptorInterface = App.injections!!.instance()

        encryptor.encryptByInternalKey(stringsConverter.toBytes(data.value))!!
    }
}