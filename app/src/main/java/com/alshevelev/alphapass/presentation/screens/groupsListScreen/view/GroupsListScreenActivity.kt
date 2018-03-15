package com.alshevelev.alphapass.presentation.screens.groupsListScreen.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.alshevelev.alphapass.R
import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.dto.entities.AccountsGroup
import com.alshevelev.alphapass.presentation.screens.addGroupScreen.AddGroupScreenActivity
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.presenter.GroupsListScreenPresenter
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.view.groupsList.GroupsListAdapter
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.viewState.ErrorViewState
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.viewState.GroupsListScreenViewState
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.viewState.ListWithGroupsViewState
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.viewState.MoveToAddGroupViewState
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.groups_list_screen.*
import kotlinx.android.synthetic.main.groups_list_screen_floating_menu.*
import java.util.concurrent.TimeUnit

class GroupsListScreenActivity: MviActivity<GroupsListScreenActivityInterface, GroupsListScreenPresenter>(),
    GroupsListScreenActivityInterface {

    private val injector = KodeinInjector()
    private val presenter: GroupsListScreenPresenter by injector.with(this).instance()

    private lateinit var groupsListAdapter: GroupsListAdapter
    private lateinit var groupsLayoutManager: LinearLayoutManager

    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.groups_list_screen)

        injector.inject(App.injections!!)
    }

    /**  */
    override fun createPresenter(): GroupsListScreenPresenter = presenter

    /** */
    override fun loadDataOnStart(): Observable<Boolean> =
        Observable.just(true).
            delay(50, TimeUnit.MILLISECONDS )

    /** User clicks Add group button */
    override fun onAddGroupMenuButtonClickIntent(): Observable<Boolean> =
        RxView.clicks(add_fab).
            flatMap { Observable.just(true) }

    /** Rendering state of the view. This method'll be called on screen rotation */
    override fun render(viewState: GroupsListScreenViewState) {
        when (viewState) {
            is ErrorViewState -> { /*Show error*/ }
            is ListWithGroupsViewState -> showList(viewState.groups)
            is MoveToAddGroupViewState -> { moveToAddGroupScreen() }

            else -> { UnsupportedOperationException("Can't process view state") }
        }
    }

    /** */
    private fun showList(groups: List<AccountsGroup>) {
        groups_list.itemAnimator = null       // To disable update list animation

        groupsLayoutManager = LinearLayoutManager(this)

        groupsListAdapter = GroupsListAdapter(groups) { }
        groupsListAdapter.setHasStableIds(true)

        groups_list.isSaveEnabled = false

        groups_list.layoutManager = groupsLayoutManager
        groups_list.adapter = groupsListAdapter
    }

    /** */
    private fun moveToAddGroupScreen() {
        startActivity(Intent(this, AddGroupScreenActivity::class.java))
    }
}