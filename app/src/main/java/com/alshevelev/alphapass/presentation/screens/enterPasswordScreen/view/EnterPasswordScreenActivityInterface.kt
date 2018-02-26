package com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.view

import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.viewState.EnterPasswordScreenViewState
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

/** */
interface EnterPasswordScreenActivityInterface: MvpView {
    /** argument - password string */
    fun onEnterButtonClickIntent(): Observable<String>

    /** */
    fun render(viewState: EnterPasswordScreenViewState);
}