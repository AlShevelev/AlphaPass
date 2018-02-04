package com.alshevelev.alphapass.core.storages.keyValue.storages

import com.alshevelev.alphapass.helpers.log

/** Base class for storage */
abstract class StorageBase: StorageInterface, StorageOperationsInstanceInterface {
    /** Read data from storage */
    override fun <T>read(readFunc: (StorageReadOperationsInterface) -> T): T = readFunc.invoke(createReadOperationsInstance())

    /** Update data in storage */
    override fun update(updateAction: (StorageWriteOperationsInterface) -> Unit) {
        var operationsInstance: StorageCommitOperationsInterface? = null

        try{
            operationsInstance = createUpdateOperationsInstance()
            updateAction.invoke(operationsInstance)
            operationsInstance.commit()
        }
        catch(ex: Exception) {
            ex.log()
            throw ex
        }
    }

    /** Create proxy for read */
    abstract override fun createReadOperationsInstance(): StorageReadOperationsInterface

    /** Create proxy for read */
    abstract override fun createUpdateOperationsInstance(): StorageCommitOperationsInterface
}