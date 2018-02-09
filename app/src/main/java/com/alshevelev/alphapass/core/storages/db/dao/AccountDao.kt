package com.alshevelev.alphapass.core.storages.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.alshevelev.alphapass.core.storages.db.entities.Account

/** */
@Dao
interface AccountDao {
    @Query("select * from account where accounts_group_id = :groupId")
    fun getAll(groupId: Long): List<Account>

    @Insert
    fun insert(account: Account)

    @Update
    fun update(account: Account)

    @Query("delete from account where id = :id")
    fun delete(id: Long)
}