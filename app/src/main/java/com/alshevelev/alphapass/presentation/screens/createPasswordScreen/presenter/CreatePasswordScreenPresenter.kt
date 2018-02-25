package com.alshevelev.alphapass.presentation.screens.createPasswordScreen.presenter

import android.content.Context
import com.alshevelev.alphapass.R
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.interactor.CreatePasswordScreenInteractorInterface
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.view.CreatePasswordScreenActivity
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.view.CreatePasswordScreenActivityInterface
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.viewState.CreatePasswordScreenViewState
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.viewState.ErrorViewState
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/** */
class CreatePasswordScreenPresenter(override val kodein: Kodein):
    MviBasePresenter<CreatePasswordScreenActivityInterface, CreatePasswordScreenViewState>(),
    KodeinAware {

    private lateinit var interactor: CreatePasswordScreenInteractorInterface

    /** */
    override fun attachView(view: CreatePasswordScreenActivityInterface) {
        super.attachView(view)
        interactor = kodein.with(view as CreatePasswordScreenActivity).instance()
    }

    /** */
    override fun bindIntents() {
        val onClickObservable = intent(CreatePasswordScreenActivityInterface::onButtonClickIntent).
            subscribeOn(Schedulers.io()).
            flatMap { interactor.processPassword(it) }.
            onErrorReturn { ErrorViewState(R.string.create_password_screen_error_common) }.
            observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(onClickObservable, CreatePasswordScreenActivityInterface::render)
    }
}