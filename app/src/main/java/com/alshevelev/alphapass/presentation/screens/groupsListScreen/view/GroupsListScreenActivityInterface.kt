package com.alshevelev.alphapass.presentation.screens.groupsListScreen.view

import com.alshevelev.alphapass.presentation.screens.groupsListScreen.viewState.GroupsListScreenViewState
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

/** */
interface GroupsListScreenActivityInterface: MvpView {
    /** argument - password string */
    fun loadDataOnStart(): Observable<Boolean>

    /** User clicks Add group button */
    fun onAddGroupMenuButtonClickIntent(): Observable<Boolean>

    /** */
    fun render(viewState: GroupsListScreenViewState)
}