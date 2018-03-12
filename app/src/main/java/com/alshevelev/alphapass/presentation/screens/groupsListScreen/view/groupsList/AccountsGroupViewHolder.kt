package com.alshevelev.alphapass.presentation.screens.groupsListScreen.view.groupsList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alshevelev.alphapass.R
import com.alshevelev.alphapass.dto.entities.AccountsGroup
import kotlinx.android.synthetic.main.groups_list_screen_item.view.*

/** */
class AccountsGroupViewHolder(
    context: Context,
    parentView: ViewGroup):
        RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.groups_list_screen_item, parentView, false)) {

    /** UI elements must be initialized here (called in ListAdapterBase::onBindViewHolder) */
    fun init(listItem: AccountsGroup, onItemClickListener: (Long) -> Unit) {
        itemView.setOnClickListener { onItemClickListener(listItem.id) }
        itemView.group_name.text = listItem.groupName
    }

    /** Used resources of UI elements must be released here (called in ListAdapterBase::onViewRecycled) */
    fun release() = itemView.setOnClickListener(null)
}