package com.alshevelev.alphapass.core.dependencyInjection

import android.arch.persistence.room.Room
import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.core.storages.db.database.Db
import com.alshevelev.alphapass.core.storages.db.database.DbRunInterface
import com.alshevelev.alphapass.core.storages.keyValue.KeyValueStorageFacade
import com.alshevelev.alphapass.core.storages.keyValue.KeyValueStorageFacadeInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageOperationsInstanceInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.combined.CombinedStorage
import com.alshevelev.alphapass.core.storages.keyValue.storages.inMemory.InMemoryStorage
import com.alshevelev.alphapass.core.storages.keyValue.storages.sharedPreferences.SharedPreferencesStorage
import com.alshevelev.alphapass.core.utility.encryption.Encryptor
import com.alshevelev.alphapass.core.utility.encryption.EncryptorInterface
import com.alshevelev.alphapass.core.utility.stringsConverter.StringsConverter
import com.alshevelev.alphapass.core.utility.stringsConverter.StringsConverterInterface
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton

/** */
val coreModule = Kodein.Module {
    //region Key-value storage
    bind<StorageOperationsInstanceInterface>("cache") with singleton { InMemoryStorage() }
    bind<StorageOperationsInstanceInterface>("persistent") with singleton { SharedPreferencesStorage() }

    bind<StorageInterface>() with singleton { CombinedStorage(App.injections!!) }

    bind<KeyValueStorageFacadeInterface>() with singleton { KeyValueStorageFacade(App.injections!!) }
    //endregion

    bind<EncryptorInterface>() with singleton { Encryptor() }

    bind<StringsConverterInterface>() with singleton { StringsConverter() }

    bind<DbRunInterface>() with singleton { Room.databaseBuilder(App.context!!, Db::class.java, "alphapass.db").build() }
}