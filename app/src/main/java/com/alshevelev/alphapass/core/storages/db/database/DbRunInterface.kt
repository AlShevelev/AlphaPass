package com.alshevelev.alphapass.core.storages.db.database

/** */
interface DbRunInterface {
    /** Run some code without transaction */
    fun <T> run(action: (DbDaoInterface) -> T): T

    /** Run some code in transaction */
    fun <T> runInTransaction(action: (DbDaoInterface) -> T): T
}