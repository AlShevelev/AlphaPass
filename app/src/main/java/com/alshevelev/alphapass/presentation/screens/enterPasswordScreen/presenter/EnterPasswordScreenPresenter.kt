package com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.presenter

import com.alshevelev.alphapass.R
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.interactor.EnterPasswordScreenInteractorInterface
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.view.EnterPasswordScreenActivity
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.view.EnterPasswordScreenActivityInterface
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.viewState.EnterPasswordScreenViewState
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.viewState.ErrorViewState
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/** */
class EnterPasswordScreenPresenter(override val kodein: Kodein):
    MviBasePresenter<EnterPasswordScreenActivityInterface, EnterPasswordScreenViewState>(),
    KodeinAware {

    private lateinit var interactor: EnterPasswordScreenInteractorInterface

    /** */
    override fun attachView(view: EnterPasswordScreenActivityInterface) {
        super.attachView(view)
        interactor = kodein.with(view as EnterPasswordScreenActivity).instance()
    }

    /** */
    override fun bindIntents() {
        val onClickObservable = intent(EnterPasswordScreenActivityInterface::onEnterButtonClickIntent).
            subscribeOn(Schedulers.io()).
            flatMap { interactor.processPassword(it) }.
            onErrorReturn { ErrorViewState(R.string.enter_password_screen_error_common) }.
            observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(onClickObservable, EnterPasswordScreenActivityInterface::render)
    }
}