package com.alshevelev.alphapass.presentation.screens.groupsListScreen.view.floatingButtonsMenu

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.widget.FrameLayout
import com.alshevelev.alphapass.R
import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.core.utility.appResources.AppResourcesInterface
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.instance


/** Menu item */
class MenuItemButton(context: Context, attrs: AttributeSet?, defStyle: Int):
    FloatingActionButton(context, attrs, defStyle) {

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context): this(context, null, 0)

    private val injector = KodeinInjector()
    private val appResources: AppResourcesInterface by injector.instance()

    private var xTranslation: Float? = null
    private var yTranslation: Float? = null
    private lateinit var showAnimation: Animation
    private lateinit var mainMenuButtonTag: String

    private var mainMenuButton: MenuMainButton? = null

    init {
        injector.inject(App.injections!!)

        processAttributes(attrs, defStyle)
    }

    /** */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        (parent as? View)?.let {
            (it.parent as? View)?.let {
                mainMenuButton = it.findViewWithTag<MenuMainButton>(mainMenuButtonTag)
            }
        }
    }

    /** */
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mainMenuButton = null
    }

    /** */
    override fun show() {
        super.show()

        visibility = View.VISIBLE

        val newLayoutParams = layoutParams as FrameLayout.LayoutParams

        newLayoutParams.rightMargin += (width * xTranslation!!).toInt()
        newLayoutParams.bottomMargin += (height * yTranslation!!).toInt()
        layoutParams = newLayoutParams

        startAnimation(showAnimation)
        isClickable = true
    }

    /** */
    override fun hide() {
        super.hide(object: FloatingActionButton.OnVisibilityChangedListener(){
            override fun onHidden(fab: FloatingActionButton?) {
                super.onHidden(fab)
                val newLayoutParams = layoutParams as FrameLayout.LayoutParams

                newLayoutParams.rightMargin -= (width * xTranslation!!).toInt()
                newLayoutParams.bottomMargin -= (height * yTranslation!!).toInt()
                layoutParams = newLayoutParams

                isClickable = false

                visibility = View.INVISIBLE
            }
        })
    }

    /** */
    override fun performClick(): Boolean {
        mainMenuButton?.setItemsVisibility(false)
        return super.performClick()
    }

    /** */
    private fun processAttributes(attrs: AttributeSet?, defStyle: Int) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.floating_button_menu_item, defStyle, 0)
        (0 until a.indexCount).
            map { a.getIndex(it) }.
            forEach {
                when(it) {
                    R.styleable.floating_button_menu_item_x_translation -> xTranslation = a.getFraction(it, 1, 1, 0f)
                    R.styleable.floating_button_menu_item_y_translation -> yTranslation = a.getFraction(it, 1, 1, 0f)
                    R.styleable.floating_button_menu_item_show_animation -> showAnimation = appResources.getAnimation(a.getResourceId(it, -1))
                    R.styleable.floating_button_menu_item_main_menu_button_tag -> mainMenuButtonTag = a.getString(it)
                }
            }
        a.recycle()
    }
}