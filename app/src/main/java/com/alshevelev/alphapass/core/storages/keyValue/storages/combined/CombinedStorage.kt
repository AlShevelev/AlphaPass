package com.alshevelev.alphapass.core.storages.keyValue.storages.combined

import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageBase
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageCommitOperationsInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageOperationsInstanceInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageReadOperationsInterface
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import java.util.concurrent.locks.ReentrantReadWriteLock

/** Storage based on shared preferences and in-memory structure for cashing */
class CombinedStorage(override val kodein: Kodein): StorageBase(), KodeinAware {

    private val cacheStorage: StorageOperationsInstanceInterface = instance("cache")
    private val persistentStorage: StorageOperationsInstanceInterface = instance("persistent")

    private val lock = ReentrantReadWriteLock()

    /** Create proxy for read */
    override fun createReadOperationsInstance(): StorageReadOperationsInterface =
        CombinedStorageReadOperations(
            lock,
            persistentStorage.createReadOperationsInstance(),
            cacheStorage.createReadOperationsInstance(),
            cacheStorage.createUpdateOperationsInstance())

    /** Create proxy for read */
    override fun createUpdateOperationsInstance(): StorageCommitOperationsInterface =
        CombinedStorageUpdateOperations(
            lock,
            persistentStorage.createUpdateOperationsInstance(),
            cacheStorage.createUpdateOperationsInstance())
}