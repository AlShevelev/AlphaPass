package com.alshevelev.alphapass.presentation.dependencyInjection

import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.interactor.CreatePasswordScreenInteractor
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.interactor.CreatePasswordScreenInteractorInterface
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.presenter.CreatePasswordScreenPresenter
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.interactor.EnterPasswordScreenInteractor
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.interactor.EnterPasswordScreenInteractorInterface
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.presenter.EnterPasswordScreenPresenter
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.interactor.GroupsListScreenInteractor
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.interactor.GroupsListScreenInteractorInterface
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.presenter.GroupsListScreenPresenter
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

    //region EnterPasswordScreen
    bind<EnterPasswordScreenPresenter>() with scopedSingleton(androidActivityScope) {
        EnterPasswordScreenPresenter(App.injections!!)
    }

    bind<EnterPasswordScreenInteractorInterface>() with scopedSingleton(androidActivityScope) {
        EnterPasswordScreenInteractor(App.injections!!)
    }
    //endregion

    //region GroupsListScreen
    bind<GroupsListScreenPresenter>() with scopedSingleton(androidActivityScope) {
        GroupsListScreenPresenter(App.injections!!)
    }

    bind<GroupsListScreenInteractorInterface>() with scopedSingleton(androidActivityScope) {
        GroupsListScreenInteractor(App.injections!!)
    }
    //endregion
}
