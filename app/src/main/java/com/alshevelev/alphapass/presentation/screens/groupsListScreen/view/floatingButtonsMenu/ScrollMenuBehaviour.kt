package com.alshevelev.alphapass.presentation.screens.groupsListScreen.view.floatingButtonsMenu

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.support.design.widget.CoordinatorLayout
import android.util.Log
import android.view.View

/** Hide button while user scrolls the list */
class ScrollMenuBehaviour(context: Context, attrs: AttributeSet):
    FloatingActionButton.Behavior(context, attrs) {

    /** */
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int): Boolean {

        Log.d("MenuBehaviour", "onStartNestedScroll")

        if (child.visibility == View.VISIBLE) {
            Log.d("MenuBehaviour", "onStartNestedScroll - hide")
            child.hide()
            (child as MenuMainButton).setItemsVisibility(false)
        }

        return true
    }

    /** */
    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View, type: Int) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type)

        Log.d("MenuBehaviour", "onStopNestedScroll")

        if (child.visibility != View.VISIBLE) {
            Log.d("MenuBehaviour", "onStopNestedScroll - show")
            child.show()
        }
    }
}