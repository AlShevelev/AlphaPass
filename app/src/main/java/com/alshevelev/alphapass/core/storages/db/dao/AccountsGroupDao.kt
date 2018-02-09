package com.alshevelev.alphapass.core.storages.db.dao

import android.arch.persistence.room.*
import com.alshevelev.alphapass.core.storages.db.entities.AccountsGroup

/** */
@Dao
interface AccountsGroupDao {
    @Query("select * from accounts_group")
    fun getAll(): List<AccountsGroup>

    @Insert
    fun insert(accountsGroup: AccountsGroup): Long

    @Update
    fun update(accountsGroup: AccountsGroup)

    @Query("delete from accounts_group where id = :id")
    fun delete(id: Long)
}