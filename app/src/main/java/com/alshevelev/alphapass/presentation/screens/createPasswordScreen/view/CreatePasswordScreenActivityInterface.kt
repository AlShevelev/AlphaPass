package com.alshevelev.alphapass.presentation.screens.createPasswordScreen.view

import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.viewState.CreatePasswordScreenViewState
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

/** */
interface CreatePasswordScreenActivityInterface: MvpView {
    /** argument - password string */
    fun onButtonClickIntent(): Observable<String>

    /** */
    fun render(viewState: CreatePasswordScreenViewState);
}