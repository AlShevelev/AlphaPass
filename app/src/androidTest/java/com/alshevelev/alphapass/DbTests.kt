package com.alshevelev.alphapass

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.support.test.runner.AndroidJUnit4
import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.core.storages.db.database.Db
import com.alshevelev.alphapass.core.storages.db.database.DbRunInterface
import com.alshevelev.alphapass.core.storages.db.entities.Account
import com.alshevelev.alphapass.core.storages.db.entities.AccountsGroup
import com.alshevelev.alphapass.core.storages.db.entities.customFields.EncryptedString
import org.junit.*
import org.junit.runner.RunWith
import org.junit.Assert.*


/** */
@RunWith(AndroidJUnit4::class)
class DbTests {
    private lateinit var db: DbRunInterface

    /** Setup before every test */
    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(App.context!!, Db::class.java).build()
    }

    /** Cleanup after every test */
    @After
    fun cleanup() {
        (db as RoomDatabase).close()
    }

    /** How to get new Id after insertion? */
    @Test
    fun insertAndAutoSetPrimaryKey() {
        val group = AccountsGroup(0, "TestGroup", 42)

        var insertedId1: Long? = null
        var insertedId2: Long? = null

        db.runInTransaction {
            insertedId1 = it.accountsGroupDao().insert(group)
            insertedId2 = it.accountsGroupDao().insert(group)
        }

        assertEquals(insertedId1, 1L)
        assertEquals(insertedId2, 2L)
    }

    /** Insert and read not null value */
    @Test
    fun insertNotNullValue() {
        val group = AccountsGroup(0, "TestGroup", 42)

        // Insert
        db.runInTransaction {
            it.accountsGroupDao().insert(group)
        }

        // Read
        val insertedGroup = db.run {
            it.accountsGroupDao().getAll()
        }.single {it.id == 1L}

        assertNotNull(insertedGroup.iconId)
        assertEquals(insertedGroup.iconId, 42)
    }

    /** Insert and read null value */
    @Test
    fun insertNullValue() {
        val group = AccountsGroup(0, "TestGroup", null)

        // Insert
        db.runInTransaction {
            it.accountsGroupDao().insert(group)
        }

        // Read
        val insertedGroup = db.run {
            it.accountsGroupDao().getAll()
        }.single {it.id == 1L}

        assertNull(insertedGroup.iconId)
    }

    /** Insert and read encrypted values (nullable and not nullable) */
    @Test
    fun insertEncryptedValue() {
        val group = AccountsGroup(0, "TestGroup", null)

        val account = Account(
            0,
            0,
            EncryptedString("account name"),     // nullable encrypted string with a value
            EncryptedString("user name"),        // not nullable encrypted string
            EncryptedString(""),                 // empty string
            null,                                // nullable encrypted string without value
            null)

        // Insert
        var insertedId: Long? = null
        db.runInTransaction {
            insertedId = it.accountsGroupDao().insert(group)

            account.groupId = insertedId!!
            it.accountDao().insert(account)
        }

        // Read
        val dbAccount = db.run {
            it.accountDao().getAll(insertedId!!)
        }.first()

        assertNotNull(dbAccount.accountName)
        assertNotNull(dbAccount.userName)
        assertNotNull(dbAccount.password)
        assertNull(dbAccount.url)

        assertEquals(dbAccount.accountName!!.value, "account name")
        assertEquals(dbAccount.userName.value, "user name")
        assertEquals(dbAccount.password!!.value, "")
    }

    /** Cascade deletion */
    @Test
    fun cascadeDelete() {
        val group = AccountsGroup(0, "TestGroup", null)
        val account1 = Account(0, 0, null, EncryptedString(""), null, null, null)
        val account2 = Account(0, 0, null, EncryptedString(""), null, null, null)

        // Insert
        var insertedId: Long? = null
        db.runInTransaction {
            insertedId = it.accountsGroupDao().insert(group)

            account1.groupId = insertedId!!
            account2.groupId = insertedId!!
            it.accountDao().insert(account1)
            it.accountDao().insert(account2)
        }

        // Read
        val totalAccountsInDb = db.run {
            it.accountDao().getAll(insertedId!!)
        }.count()

        // Delete
        db.run {
            it.accountsGroupDao().delete(insertedId!!)
        }

        // Read
        val totalAccountsInDbAfterDeletion = db.run {
            it.accountDao().getAll(insertedId!!)
        }.count()

        assertEquals(totalAccountsInDb, 2)
        assertEquals(totalAccountsInDbAfterDeletion, 0)
    }
}