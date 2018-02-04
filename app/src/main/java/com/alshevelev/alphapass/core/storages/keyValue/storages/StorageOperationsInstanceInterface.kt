package com.alshevelev.alphapass.core.storages.keyValue.storages

/** */
interface StorageOperationsInstanceInterface {
    /** Create proxy for read */
    fun createReadOperationsInstance(): StorageReadOperationsInterface

    /** Create proxy for read */
    fun createUpdateOperationsInstance(): StorageCommitOperationsInterface
}