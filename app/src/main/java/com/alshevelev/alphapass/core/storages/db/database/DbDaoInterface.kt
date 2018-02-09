package com.alshevelev.alphapass.core.storages.db.database

import com.alshevelev.alphapass.core.storages.db.dao.AccountDao
import com.alshevelev.alphapass.core.storages.db.dao.AccountsGroupDao

/** */
interface DbDaoInterface {
    /** */
    fun accountDao(): AccountDao

    /** */
    fun accountsGroupDao(): AccountsGroupDao
}