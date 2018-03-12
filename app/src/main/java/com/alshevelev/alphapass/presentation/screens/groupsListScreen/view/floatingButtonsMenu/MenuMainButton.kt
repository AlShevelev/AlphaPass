package com.alshevelev.alphapass.presentation.screens.groupsListScreen.view.floatingButtonsMenu

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.view.View
import com.alshevelev.alphapass.R

/** */
class MenuMainButton(context: Context, attrs: AttributeSet?, defStyle: Int):
    FloatingActionButton(context, attrs, defStyle) {

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context): this(context, null, 0)

    private var menuItems: List<MenuItemButton>? = null
    private var menuItemsTags: List<String>

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.floating_button_menu, defStyle, 0)
        menuItemsTags = a.getString(a.getIndex(0)).split(";")
        a.recycle()

        setOnClickListener { processClick() }
    }

    /** */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        menuItems = (parent as? View)?.let { parent ->
            menuItemsTags.map { parent.findViewWithTag<MenuItemButton>(it) }.toList()
        }
    }

    /** */
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        menuItems = null
    }

    /** */
    override fun hide() {
        super.hide(object: FloatingActionButton.OnVisibilityChangedListener(){
            override fun onHidden(fab: FloatingActionButton?) {
                super.onHidden(fab)
                fab!!.visibility = View.INVISIBLE
            }
        })
    }

    /** */
    fun setItemsVisibility(isVisible: Boolean) {
        menuItems?.let {
            it.forEach { if(isVisible) it.show() else it.hide() }
        }
    }

    /** */
    private fun processClick() {
        menuItems?.let {
            if(!it.isEmpty()) {
                setItemsVisibility(it[0].visibility != View.VISIBLE)
            }
        }
    }
}
