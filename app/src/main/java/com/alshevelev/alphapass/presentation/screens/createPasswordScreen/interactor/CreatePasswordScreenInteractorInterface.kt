package com.alshevelev.alphapass.presentation.screens.createPasswordScreen.interactor

import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.viewState.CreatePasswordScreenViewState
import io.reactivex.Observable

/** */
interface CreatePasswordScreenInteractorInterface {

    /** */
    fun processPassword(password: String): Observable<CreatePasswordScreenViewState>
}