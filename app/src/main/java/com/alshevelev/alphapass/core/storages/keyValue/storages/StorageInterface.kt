package com.alshevelev.alphapass.core.storages.keyValue.storages

/** External interface of storage */
interface StorageInterface {
    /** Read data from storage */
    fun <T>read(readFunc: (StorageReadOperationsInterface) -> T): T

    /** Update data in storage */
    fun update(updateAction: (StorageWriteOperationsInterface) -> Unit)
}