package com.alshevelev.alphapass.core.storages.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import com.alshevelev.alphapass.core.storages.db.entities.customFields.EncryptedString

/** */
@Suppress("ArrayInDataClass")
@Entity(
    tableName = "account",
    foreignKeys = [
        ForeignKey(
            entity = AccountsGroup::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("accounts_group_id"),
            onDelete = CASCADE
    )])
data class Account(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "accounts_group_id")
    var groupId: Long = 0,

    @ColumnInfo(name = "account_name", typeAffinity = ColumnInfo.BLOB)
    var accountName: EncryptedString?,

    @ColumnInfo(name = "user_name", typeAffinity = ColumnInfo.BLOB)
    var userName: EncryptedString,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var password: EncryptedString?,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var url: EncryptedString?,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var notes: EncryptedString?
)