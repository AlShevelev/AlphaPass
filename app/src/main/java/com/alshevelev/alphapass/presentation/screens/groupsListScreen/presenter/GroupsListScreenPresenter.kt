package com.alshevelev.alphapass.presentation.screens.groupsListScreen.presenter

import android.util.Log
import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.interactor.GroupsListScreenInteractor
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.interactor.GroupsListScreenInteractorInterface
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.view.GroupsListScreenActivity
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.view.GroupsListScreenActivityInterface
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.viewState.ErrorViewState
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.viewState.GroupsListScreenViewState
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.viewState.ListWithGroupsViewState
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GroupsListScreenPresenter(override val kodein: Kodein):
    MviBasePresenter<GroupsListScreenActivityInterface, GroupsListScreenViewState>(),
    KodeinAware {

    private lateinit var interactor: GroupsListScreenInteractorInterface

    /** */
    override fun attachView(view: GroupsListScreenActivityInterface) {
        interactor = kodein.with(view as GroupsListScreenActivity).instance()

        super.attachView(view)
    }

    /** */
    override fun bindIntents() {
        val onLoadDataOnStartObservable = intent(GroupsListScreenActivityInterface::loadDataOnStart).
            subscribeOn(Schedulers.io()).
            flatMap {
                interactor.getAllGroups()
            }.
            map {
                ListWithGroupsViewState(it) as GroupsListScreenViewState
            }.
            onErrorReturn { ErrorViewState() }.
            observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(onLoadDataOnStartObservable, GroupsListScreenActivityInterface::render)
    }
}