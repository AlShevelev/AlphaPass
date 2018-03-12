package com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.interactor

import com.alshevelev.alphapass.R
import com.alshevelev.alphapass.core.storages.keyValue.KeyValueStorageInterface
import com.alshevelev.alphapass.core.utility.encryption.EncryptorInterface
import com.alshevelev.alphapass.core.utility.stringsConverter.StringsConverterInterface
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.viewState.EnterPasswordScreenViewState
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.viewState.ErrorViewState
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.viewState.SuccessViewState
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import io.reactivex.Observable

/** */
class EnterPasswordScreenInteractor(override val kodein: Kodein): EnterPasswordScreenInteractorInterface, KodeinAware {

    private val stringsConverter: StringsConverterInterface = instance()
    private val encryptor: EncryptorInterface = instance()
    private val keyValueStorage: KeyValueStorageInterface = instance()

    /** */
    override fun processPassword(password: String): Observable<EnterPasswordScreenViewState> =
        Observable.fromCallable {
            if(password.isEmpty()) {
                return@fromCallable ErrorViewState(R.string.enter_password_screen_error_invalid_pass)
            }

            if(password != extractPassword(keyValueStorage.readPassword())) {
                return@fromCallable ErrorViewState(R.string.enter_password_screen_error_invalid_pass)
            }
            else {
                SuccessViewState()
            }
        }

    private fun extractPassword(encryptedPass: String): String =
        stringsConverter.fromBytes(encryptor.decryptByInternalKey(stringsConverter.fromBase64(encryptedPass))!!)
}