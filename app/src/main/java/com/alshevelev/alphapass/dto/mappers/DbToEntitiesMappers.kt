package com.alshevelev.alphapass.dto.mappers

import com.alshevelev.alphapass.core.storages.db.entities.customFields.EncryptedString
import com.alshevelev.alphapass.core.storages.db.entities.Account as AccountDb
import com.alshevelev.alphapass.core.storages.db.entities.AccountsGroup as AccountsGroupDb

import com.alshevelev.alphapass.dto.entities.Account
import com.alshevelev.alphapass.dto.entities.AccountsGroup


//region Account
fun Account.map(accountsGroupId: Long): AccountDb {
    return AccountDb(
        this.id,
        accountsGroupId,
        this.accountName?.let { EncryptedString(it) },
        EncryptedString(this.userName),
        this.password?.let { EncryptedString(it) },
        this.url?.let{ EncryptedString(it) },
        this.notes?.let { EncryptedString(it) }
    )
}

fun AccountDb.map(): Account {
    return Account(
        this.id,
        this.accountName?.value,
        this.userName.value,
        this.password?.value,
        this.url?.value,
        this.notes?.value
    )
}
//endregion

//region AccountsGroup
fun AccountsGroup.map(): AccountsGroupDb {
    return AccountsGroupDb(
        this.id,
        this.groupName,
        this.iconId
    )
}

fun AccountsGroupDb.map(): AccountsGroup {
    return AccountsGroup(
        this.id,
        this.groupName,
        this.iconId
    )
}
//endregion
