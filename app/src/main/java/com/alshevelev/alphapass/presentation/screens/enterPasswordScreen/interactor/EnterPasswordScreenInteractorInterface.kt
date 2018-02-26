package com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.interactor

import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.viewState.EnterPasswordScreenViewState
import io.reactivex.Observable

/** */
interface EnterPasswordScreenInteractorInterface {
    /** */
    fun processPassword(password: String): Observable<EnterPasswordScreenViewState>
}