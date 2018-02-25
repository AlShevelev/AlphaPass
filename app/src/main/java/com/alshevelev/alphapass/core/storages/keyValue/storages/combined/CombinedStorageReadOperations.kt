package com.alshevelev.alphapass.core.storages.keyValue.storages.combined

import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageCommitOperationsInterface
import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageReadOperationsInterface
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

/** */
class CombinedStorageReadOperations(
    private val lock: ReentrantReadWriteLock,
    private val persistentStorage: StorageReadOperationsInterface,
    private val cacheStorageRead: StorageReadOperationsInterface,
    private val cacheStorageUpdate: StorageCommitOperationsInterface): StorageReadOperationsInterface {

    /** Check is item exists  */
    override fun contains(key: String): Boolean {
        lock.read {
            return if(cacheStorageRead.contains(key)) {
                true
            } else {
                persistentStorage.contains(key)
            }
        }
    }

    /** Read boolean value  */
    override fun readBoolean(key: String): Boolean? {
        return read(
            { cacheStorageRead.readBoolean(key) },
            { persistentStorage.readBoolean(key) },
            { cacheStorageUpdate.putBoolean(key, it) }
        )
    }

    /** Checks equality of [valueToCompare] and a stored Boolean value  */
    override fun areEqualsBoolean(key: String, valueToCompare: Boolean): Boolean = readBoolean(key) == valueToCompare

    /** Read string value  */
    override fun readString(key: String): String? {
        return read(
            { cacheStorageRead.readString(key) },
            { persistentStorage.readString(key) },
            { cacheStorageUpdate.putString(key, it) }
        )
    }

    /** Checks equality of [valueToCompare] and a stored String value  */
    override fun areEqualsString(key: String, valueToCompare: String): Boolean = readString(key) == valueToCompare

    /** Read float value  */
    override fun readFloat(key: String): Float? {
        return read(
            { cacheStorageRead.readFloat(key) },
            { persistentStorage.readFloat(key) },
            { cacheStorageUpdate.putFloat(key, it) }
        )
    }

    /** Checks equality of [valueToCompare] and a stored Float value  */
    override fun areEqualsFloat(key: String, valueToCompare: Float): Boolean = readFloat(key) == valueToCompare

    /** Read int value  */
    override fun readInt(key: String): Int? {
        return read(
            { cacheStorageRead.readInt(key) },
            { persistentStorage.readInt(key) },
            { cacheStorageUpdate.putInt(key, it) }
        )
    }

    /** Checks equality of [valueToCompare] and a stored Int value  */
    override fun areEqualsInt(key: String, valueToCompare: Int): Boolean = readInt(key) == valueToCompare

    /** Read long value  */
    override fun readLong(key: String): Long? {
        return read(
            { cacheStorageRead.readLong(key) },
            { persistentStorage.readLong(key) },
            { cacheStorageUpdate.putLong(key, it) }
        )
    }

    /** Checks equality of [valueToCompare] and a stored long value  */
    override fun areEqualsLong(key: String, valueToCompare: Long): Boolean = readLong(key) == valueToCompare

    /** */
    private fun <T>read(
        readFromCacheAction: () -> T?,
        readFromPersistentStorageAction: () -> T?,
        writeToCacheAction: (T) -> Unit): T? {

        var value: T? = null
        var needToUpdateCache = false

        lock.read {
            value = readFromCacheAction.invoke()
            if(value != null) {
                return value
            }
            else {
                value = readFromPersistentStorageAction.invoke()
                if(value == null) {
                    return null
                }
                else {
                    needToUpdateCache = true
                }
            }
        }

        if(needToUpdateCache) {
            lock.write {
                writeToCacheAction.invoke(value!!)
            }
        }

        return value
    }
}