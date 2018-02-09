package com.alshevelev.alphapass.core.storages.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "accounts_group")
data class AccountsGroup(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "group_name")
    var groupName: String,

    @ColumnInfo(name = "icon_id")
    var iconId: Int?)
