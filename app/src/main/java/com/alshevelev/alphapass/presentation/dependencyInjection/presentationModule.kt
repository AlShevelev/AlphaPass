package com.alshevelev.alphapass.presentation.dependencyInjection

import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.interactor.CreatePasswordScreenInteractor
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.interactor.CreatePasswordScreenInteractorInterface
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.presenter.CreatePasswordScreenPresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.androidActivityScope
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.scopedSingleton

/** */
val presentationModule = Kodein.Module {
    //region CreatePasswordScreen
    bind<CreatePasswordScreenPresenter>() with scopedSingleton(androidActivityScope) {
        CreatePasswordScreenPresenter(App.injections!!)
    }

    bind<CreatePasswordScreenInteractorInterface>() with scopedSingleton(androidActivityScope) {
        CreatePasswordScreenInteractor(App.injections!!)
    }
    //endregion
}