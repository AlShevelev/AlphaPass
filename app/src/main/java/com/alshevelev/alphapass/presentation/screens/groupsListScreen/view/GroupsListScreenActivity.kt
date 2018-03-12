package com.alshevelev.alphapass.presentation.screens.groupsListScreen.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.alshevelev.alphapass.R
import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.presenter.GroupsListScreenPresenter
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.view.groupsList.GroupsListAdapter
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.viewState.ErrorViewState
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.viewState.GroupsListScreenViewState
import com.alshevelev.alphapass.presentation.screens.groupsListScreen.viewState.ListWithGroupsViewState
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with
import com.hannesdorfmann.mosby3.mvi.MviActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.groups_list_screen.*
import kotlinx.android.synthetic.main.groups_list_screen_floating_menu.*

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

    override fun loadDataOnStart(): Observable<Boolean> =
        Observable.just(true).
            doOnComplete { Log.d("MVI_FACK", "loadDataOnStart()") }

    /** Rendering state of the view. This method'll be called on screen rotation */
    override fun render(viewState: GroupsListScreenViewState) {
        Log.d("MVI_FACK", "render")
        when (viewState) {
            is ErrorViewState -> { Log.d("MVI_FACK", "error") }

            is ListWithGroupsViewState -> {
                Log.d("MVI_FACK", "list ${viewState.groups.size}")

                groups_list.itemAnimator = null       // To disable update list animation

                groupsLayoutManager = LinearLayoutManager(this)

                groupsListAdapter = GroupsListAdapter(viewState.groups) { }
                groupsListAdapter.setHasStableIds(true)

                groups_list.isSaveEnabled = false

                groups_list.layoutManager = groupsLayoutManager
                groups_list.adapter = groupsListAdapter
            }

            else -> { Log.d("MVI_FACK", "can't process view state") }
        }
    }
}