package com.alshevelev.alphapass.core.storages.keyValue.storages.inMemory

import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageBase
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageCommitOperationsInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageReadOperationsInterface
import java.util.*

/** Storage based on in-memory dictionary */
class InMemoryStorage: StorageBase() {
    private val storage: MutableMap<String, Any> = TreeMap()

    /** Create proxy for read */
    override fun createReadOperationsInstance(): StorageReadOperationsInterface = InMemoryStorageOperations(storage)

    /** Create proxy for read */
    override fun createUpdateOperationsInstance(): StorageCommitOperationsInterface = InMemoryStorageOperations(storage)
}