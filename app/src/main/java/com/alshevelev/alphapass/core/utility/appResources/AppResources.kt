package com.alshevelev.alphapass.core.utility.appResources

import android.support.annotation.ArrayRes
import android.support.annotation.BoolRes
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.res.ResourcesCompat
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.alshevelev.alphapass.R
import com.alshevelev.alphapass.app.App
import java.text.MessageFormat

/** Service for providing application resources from resources files  */
class AppResources: AppResourcesInterface {
    //region AppResourcesInterface methods
    /** Get color from resources  */
    override fun getColor(@ColorRes resId: Int): Int = ResourcesCompat.getColor(App.context!!.resources, resId, null)

    /** Get string resource  */
    override fun getString(@StringRes resId: Int): String = App.context!!.getString(resId)

    /** Get string and format it */
    override fun getFormattedString(resId: Int, arg1: Any): String = MessageFormat.format(getString(resId), arg1)

    /** Get string and format it */
    override fun getFormattedString(resId: Int, arg1: Any, arg2: Any): String = MessageFormat.format(getString(resId), arg1, arg2)

    /** Get string resource  */
    override fun getBoolean(@BoolRes resId: Int): Boolean = App.context!!.resources.getBoolean(resId)

    /** Get array of strings  */
    override fun getStringsList(@ArrayRes resId: Int) : List<String> = App.context!!.resources.getStringArray(resId).toList()

    /** Get current locale */
    override fun getLocale(): String = getString(R.string.locale_info)

    /** Get dimension value in [px]  */
    override fun getDimension(resId: Int): Float = App.context!!.resources.getDimension(resId)

    /** Get animation */
    override fun getAnimation(resId: Int): Animation = AnimationUtils.loadAnimation(App.context!!, resId)

    /** Get fraction */
    override fun getFraction(resId: Int, base: Int): Float = App.context!!.resources.getFraction(resId, base, base)
    //endregion
}