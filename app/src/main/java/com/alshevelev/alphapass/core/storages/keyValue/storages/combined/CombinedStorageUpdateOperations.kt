package com.alshevelev.alphapass.core.storages.keyValue.storages.combined

import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageCommitOperationsInterface
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.write

/** */
class CombinedStorageUpdateOperations(
    private val lock: ReentrantReadWriteLock,
    private val persistentStorage: StorageCommitOperationsInterface,
    private val cacheStorage: StorageCommitOperationsInterface): StorageCommitOperationsInterface {

    /** Put boolean value  */
    override fun putBoolean(key: String, value: Boolean) {
        lock.write {
            persistentStorage.putBoolean(key, value)
            cacheStorage.putBoolean(key, value)
        }
    }

    /** Put string value  */
    override fun putString(key: String, value: String) {
        lock.write {
            persistentStorage.putString(key, value)
            cacheStorage.putString(key, value)
        }
    }

    /** Put float value  */
    override fun putFloat(key: String, value: Float) {
        lock.write {
            persistentStorage.putFloat(key, value)
            cacheStorage.putFloat(key, value)
        }
    }

    /** Put int value  */
    override fun putInt(key: String, value: Int) {
        lock.write {
            persistentStorage.putInt(key, value)
            cacheStorage.putInt(key, value)
        }
    }

    /** Put long value  */
    override fun putLong(key: String, value: Long) {
        lock.write {
            persistentStorage.putLong(key, value)
            cacheStorage.putLong(key, value)
        }
    }

    /** Remove value by key  */
    override fun remove(key: String) {
        lock.write {
            persistentStorage.remove(key)
            cacheStorage.remove(key)
        }
    }

    /** Remove all values  */
    override fun removeAll() {
        lock.write {
            persistentStorage.removeAll()
            cacheStorage.removeAll()
        }
    }

    /** Complete editing  */
    override fun commit() {
        lock.write {
            persistentStorage.commit()
            cacheStorage.commit()
        }
    }
}