package com.alshevelev.alphapass

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.core.storages.db.database.Db
import com.alshevelev.alphapass.core.storages.db.database.DbRunInterface
import com.alshevelev.alphapass.core.utility.encryption.Encryptor
import com.alshevelev.alphapass.core.utility.stringsConverter.StringsConverter
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Alexander.Shevelev on 08-Feb-18.
 */
@RunWith(AndroidJUnit4::class)
class Tests2 {
    companion object {
        private lateinit var encryptor: Encryptor
        private lateinit var converter: StringsConverter
        private lateinit var db: DbRunInterface

        @BeforeClass
        @JvmStatic
        fun setUp() {
            converter = StringsConverter()
            encryptor = Encryptor()
            db = Room.inMemoryDatabaseBuilder(App.context!!, Db::class.java).build()
            Log.d("", "")
        }


//        private lateinit var db: DbRunInterface
//
//        @BeforeClass
//        @JvmStatic
//        fun setUp() {
//            db = Room.inMemoryDatabaseBuilder(App.context!!, Db::class.java).build()
//        }
//
//        @AfterClass
//        @JvmStatic
//        fun tearDown() {
//            (db as RoomDatabase).close()
//        }
    }

    @Test
    fun test() {

    }
}