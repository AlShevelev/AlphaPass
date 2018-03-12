package com.alshevelev.alphapass.presentation.screens.groupsListScreen.interactor

import com.alshevelev.alphapass.dto.entities.AccountsGroup
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import io.reactivex.Observable

/** */
class GroupsListScreenInteractor(override val kodein: Kodein): GroupsListScreenInteractorInterface, KodeinAware {

    /** */
    override fun getAllGroups(): Observable<List<AccountsGroup>> =
        Observable.fromCallable {
            listOf(
                AccountsGroup(1, "group 1", null),
                AccountsGroup(2, "group 2", null),
                AccountsGroup(3, "group 3", null),
                AccountsGroup(4, "group 4", null),
                AccountsGroup(5, "group 5", null),
                AccountsGroup(6, "group 6", null),
                AccountsGroup(7, "group 7", null),
                AccountsGroup(8, "group 8", null),
                AccountsGroup(9, "group 9", null),
                AccountsGroup(10, "group 10", null),
                AccountsGroup(11, "group 11", null),
                AccountsGroup(12, "group 12", null),
                AccountsGroup(13, "group 13", null),
                AccountsGroup(14, "group 14", null),
                AccountsGroup(15, "group 15", null),
                AccountsGroup(16, "group 16", null),
                AccountsGroup(17, "group 17", null),
                AccountsGroup(18, "group 18", null),
                AccountsGroup(19, "group 19", null),
                AccountsGroup(20, "group 20", null),
                AccountsGroup(21, "group 21", null),
                AccountsGroup(22, "group 22", null),
                AccountsGroup(23, "group 23", null),
                AccountsGroup(24, "group 24", null),
                AccountsGroup(25, "group 25", null),
                AccountsGroup(26, "group 26", null),
                AccountsGroup(27, "group 27", null),
                AccountsGroup(28, "group 28", null),
                AccountsGroup(29, "group 29", null),
                AccountsGroup(30, "group 30", null))
        }
}