package com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.view

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import com.alshevelev.alphapass.R
import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.core.utility.appResources.AppResourcesInterface
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.presenter.EnterPasswordScreenPresenter
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.viewState.EnterPasswordScreenViewState
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.viewState.ErrorViewState
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.viewState.SuccessViewState
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.view.GroupsListScreenActivity
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.enter_password_screen.*

class EnterPasswordScreenActivity: MviActivity<EnterPasswordScreenActivityInterface, EnterPasswordScreenPresenter>(),
    EnterPasswordScreenActivityInterface {

    private val injector = KodeinInjector()
    private val appResources: AppResourcesInterface by injector.instance()
    private val presenter: EnterPasswordScreenPresenter by injector.with(this).instance()

    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_password_screen)

        injector.inject(App.injections!!)

        password_field.setOnEditorActionListener { v, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                enter_password_button.performClick()
            }
            false
        }
    }

    /**  */
    override fun createPresenter(): EnterPasswordScreenPresenter = presenter

    /** */
    override fun onEnterButtonClickIntent(): Observable<String> =
        RxView.clicks(enter_password_button).
            flatMap { Observable.just(password_field.text.toString()) }

    /** Rendering state of the view. This method'll be called on screen rotation */
    override fun render(viewState: EnterPasswordScreenViewState) {
        when (viewState) {
            is ErrorViewState -> {
                warning.visibility = View.VISIBLE
                warning.text = appResources.getString(viewState.errorResourceId)
            }

            is SuccessViewState -> {
                startActivity(Intent(this, GroupsListScreenActivity::class.java))
                finish()
            }

            else -> { UnsupportedOperationException("Can't process this state: ${viewState::class.simpleName}")}
        }
    }
}