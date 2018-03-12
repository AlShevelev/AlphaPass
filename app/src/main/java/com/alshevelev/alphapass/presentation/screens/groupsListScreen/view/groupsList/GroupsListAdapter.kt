package com.alshevelev.alphapass.presentation.screens.groupsListScreen.view.groupsList

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.alshevelev.alphapass.dto.entities.AccountsGroup

/** */
class GroupsListAdapter(
    private val items: List<AccountsGroup>,
    private val onItemClickListener: (Long) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    /** */
    override fun getItemCount(): Int = items.size

    /** */
    override fun getItemId(position: Int): Long = items[position].id

    /** */
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
        AccountsGroupViewHolder(parent!!.context, parent)

    /** */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) =
        (holder as AccountsGroupViewHolder).init(items[position], onItemClickListener)

    /** */
    override fun onViewRecycled(holder: RecyclerView.ViewHolder?) = (holder as AccountsGroupViewHolder).release()
}