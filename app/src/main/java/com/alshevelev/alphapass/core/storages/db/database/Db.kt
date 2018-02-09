package com.alshevelev.alphapass.core.storages.db.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.alshevelev.alphapass.core.storages.db.dao.AccountDao
import com.alshevelev.alphapass.core.storages.db.dao.AccountsGroupDao
import com.alshevelev.alphapass.core.storages.db.entities.Account
import com.alshevelev.alphapass.core.storages.db.entities.AccountsGroup
import com.alshevelev.alphapass.core.storages.db.typeConverters.CustomTypesConverters

/** */
@Database(entities = [Account::class, AccountsGroup::class], version = 1)
@TypeConverters(CustomTypesConverters::class)
abstract class Db: RoomDatabase(), DbDaoInterface, DbRunInterface {
    //region DbRunInterface methods
    /** Run some code without transaction */
    override fun <T>run(action: (DbDaoInterface) -> T): T = action.invoke(this)

    /** Run some code in transaction */
    override fun <T>runInTransaction(action: (DbDaoInterface) -> T): T {
        beginTransaction()

        try {
            val result = action.invoke(this)
            setTransactionSuccessful()
            return result
        }
        catch (ex: Exception) {
            ex.printStackTrace()
            throw ex
        }
        finally {
            endTransaction()
        }
    }
    //endregion

    //region DbDaoInterface methods
    /** */
    abstract override fun accountDao(): AccountDao

    /** */
    abstract override fun accountsGroupDao(): AccountsGroupDao
    //endregion
}