package com.alshevelev.alphapass.presentation.screens.createPasswordScreen.interactor

import com.alshevelev.alphapass.R
import com.alshevelev.alphapass.core.storages.keyValue.KeyValueStorageFacadeInterface
import com.alshevelev.alphapass.core.utility.encryption.EncryptorInterface
import com.alshevelev.alphapass.core.utility.stringsConverter.StringsConverterInterface
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.viewState.CreatePasswordScreenViewState
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.viewState.ErrorViewState
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.viewState.SuccessViewState
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import io.reactivex.Observable

/** */
class CreatePasswordScreenInteractor(override val kodein: Kodein): CreatePasswordScreenInteractorInterface, KodeinAware {

    private val stringsConverter: StringsConverterInterface = instance()
    private val encryptor: EncryptorInterface = instance()
    private val keyValueStorage: KeyValueStorageFacadeInterface = instance()

    /** */
    override fun processPassword(password: String): Observable<CreatePasswordScreenViewState> =
        Observable.fromCallable {
            if(password.length < 6 ) {
                ErrorViewState(R.string.create_password_screen_error_too_shot)
            }
            else {
                keyValueStorage.writeOrUpdatePassword(preparePassToWrite(password))

                SuccessViewState()
            }
        }

    private fun preparePassToWrite(password: String): String =
        stringsConverter.toBase64(encryptor.encryptByInternalKey(stringsConverter.toBytes(password))!!)
}