package com.alshevelev.alphapass.presentation.screens.groupsListScreen.interactor

import com.alshevelev.alphapass.dto.entities.AccountsGroup
import io.reactivex.Observable

/** */
interface GroupsListScreenInteractorInterface {
    /** */
    fun getAllGroups(): Observable<List<AccountsGroup>>
}