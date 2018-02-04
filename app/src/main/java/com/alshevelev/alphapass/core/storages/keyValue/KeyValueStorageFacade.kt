package com.alshevelev.alphapass.core.storages.keyValue

import com.alshevelev.alphapass.core.storages.keyValue.storages.StorageInterface
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance

/** Helper class for access to App-level private shared preferences  */
class KeyValueStorageFacade(override val kodein: Kodein): KeyValueStorageFacadeInterface, KodeinAware {

    private val keyValueStorage: StorageInterface = instance()

//    private companion object {
//        const val CREDENTIALS_USER_NAME_KEY : String = "CREDENTIALS_USER_NAME_KEY"
//        const val CREDENTIALS_PASSWORD_KEY : String = "CREDENTIALS_PASSWORD_KEY"
//
//        const val LAST_USED_SERVERS_KEY: String = "LAST_USED_SERVERS_KEY"
//
//        const val SERVER_DATE_TIME_DELTA_KEY: String = "SERVER_DATE_TIME_DELTA_KEY"
//
//        const val CURRENT_USER_PROFILE_ID_KEY: String = "CURRENT_USER_PROFILE_ID_KEY"
//        const val CURRENT_USER_NAME_KEY: String = "CURRENT_USER_NAME_KEY"
//        const val CURRENT_USER_AVATAR_ID_KEY: String = "CURRENT_USER_AVATAR_ID_KEY"
//
//        const val FCM_TOKEN_KEY: String = "FCM_TOKEN_KEY"
//        const val FCM_TOKEN_WAS_SENT_TO_SERVER_KEY: String = "FCM_TOKEN_WAS_SENT_TO_SERVER_KEY"
//    }
//
//    /** */
//    override fun saveUserCredentials(userCredentialsInfo: UserCredentialsBriefInfo) =
//        keyValueStorage.update {
//            it.putBytes(CREDENTIALS_USER_NAME_KEY, keystoreService.encrypt(userCredentialsInfo.userName))
//            it.putBytes(CREDENTIALS_PASSWORD_KEY, keystoreService.encrypt(userCredentialsInfo.password))
//        }
//
//    /** */
//    override fun loadUserCredentials(): UserCredentialsBriefInfo =
//        keyValueStorage.read {
//            UserCredentialsBriefInfo(
//                keystoreService.decrypt(it.readBytes(CREDENTIALS_USER_NAME_KEY)),
//                keystoreService.decrypt(it.readBytes(CREDENTIALS_PASSWORD_KEY)))
//        }
//
//    /** Save info of current user */
//    override fun saveCurrentUserInfo(currentUser: CurrentUser) =
//        keyValueStorage.update {
//            it.putString(CURRENT_USER_PROFILE_ID_KEY, currentUser.profileId)
//            it.putString(CURRENT_USER_NAME_KEY, currentUser.name)
//            it.putString(CURRENT_USER_AVATAR_ID_KEY, currentUser.avatarId ?: IdUtil.emptyId)
//        }
//
//    /** Remove info of current user */
//    override fun removeCurrentUserInfo() {
//        keyValueStorage.update {
//            it.remove(CURRENT_USER_PROFILE_ID_KEY)
//            it.remove(CURRENT_USER_NAME_KEY)
//            it.remove(CURRENT_USER_AVATAR_ID_KEY)
//        }
//    }
//
//    /** Save info of current user */
//    override fun loadCurrentUserInfo(): CurrentUser =
//        keyValueStorage.read {
//            CurrentUser(
//                it.readString(CURRENT_USER_PROFILE_ID_KEY)!!,
//                it.readString(CURRENT_USER_NAME_KEY)!!,
//                it.readString(CURRENT_USER_AVATAR_ID_KEY)?.let{ if(it != IdUtil.emptyId) it else null})
//        }
//
//    /** Load Id of current user */
//    override fun loadProfileId(): String? = loadString(CURRENT_USER_PROFILE_ID_KEY)
//
//    /** */
//    override fun removeUserPassword() = remove(CREDENTIALS_PASSWORD_KEY)
//
//    /** Save list of user servers */
//    override fun saveLastUsedServers(servers: List<String>) {
//        val dataToSave = if(servers.isNotEmpty()) servers.toTypedArray().joinToString(";") else ""
//        keyValueStorage.update {
//            it.putString(LAST_USED_SERVERS_KEY, dataToSave)
//        }
//    }
//
//    /** Load list of user servers */
//    override fun loadLastUsedServers(): List<String> =
//        keyValueStorage.read {
//            it.readString(LAST_USED_SERVERS_KEY).let{ servers ->
//                if(servers.isNullOrEmpty()) {
//                    listOf()
//                }
//                else
//                    servers!!.split(";")
//            }
//        }
//
//    /** Save delta between server and client UTC date/time (ms) */
//    override fun saveServerTimestampDelta(delta: Long) =
//        keyValueStorage.update {
//            it.putLong(SERVER_DATE_TIME_DELTA_KEY, delta)
//        }
//
//    /** Load delta between server and client UTC date/time (ms) */
//    override fun loadServerTimestampDelta(): Long =
//        keyValueStorage.read {
//            it.readLong(SERVER_DATE_TIME_DELTA_KEY) ?: 0
//        }
//
//    /** Save Firebase Cloud Messaging token */
//    override fun saveFCMToken(token: String) = saveString(FCM_TOKEN_KEY, token)
//
//    /** Load Firebase Cloud Messaging token
//     * (null - token not found */
//    override fun loadFCMToken(): String? = loadString(FCM_TOKEN_KEY)
//
//    /** Remove Firebase Cloud Messaging token */
//    override fun removeFCMToken() = remove(FCM_TOKEN_KEY)
//
//    /** Update Firebase Cloud Messaging token was sent to sever flag*/
//    override fun updateFCMTokenSentToServerState(state: Boolean) =
//        keyValueStorage.update {
//            it.putBoolean(FCM_TOKEN_WAS_SENT_TO_SERVER_KEY, state)
//        }
//
//    /** Check Firebase Cloud Messaging token was sent to sever flag*/
//    override fun isFCMTokenSentToServer(): Boolean =
//        keyValueStorage.read {
//            it.readBoolean(FCM_TOKEN_WAS_SENT_TO_SERVER_KEY) ?: false
//        }
//
//    /** */
//    private fun remove(key: String) =
//        keyValueStorage.update {
//            it.remove(key)
//        }
//
//    /** */
//    private fun saveString(key: String, value: String) =
//        keyValueStorage.update {
//            it.putString(key, value)
//        }
//
//    /** */
//    private fun loadString(key: String): String? =
//        keyValueStorage.read {
//            it.readString(key).let { if(it =="" ) null else it }
//        }
}