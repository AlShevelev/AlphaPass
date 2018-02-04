package com.alshevelev.alphapass.core.dependencyInjection

import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.core.storages.keyValue.KeyValueStorageFacade
import com.alshevelev.alphapass.core.storages.keyValue.KeyValueStorageFacadeInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageOperationsInstanceInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.combined.CombinedStorage
import com.alshevelev.alphapass.core.storages.keyValue.storages.inMemory.InMemoryStorage
import com.alshevelev.alphapass.core.storages.keyValue.storages.sharedPreferences.SharedPreferencesStorage
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
}