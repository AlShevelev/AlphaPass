package com.alshevelev.alphapass.core.utility.appResources

import android.support.annotation.*
import android.view.animation.Animation

/** Interface for AppResources  */
interface AppResourcesInterface {
    /** Get color from resources  */
    fun getColor(@ColorRes resId: Int): Int

    /** Get string resource  */
    fun getString(@StringRes resId: Int): String

    /** Get string and format it */
    fun getFormattedString(@StringRes resId: Int, arg1: Any): String

    /** Get string and format it */
    fun getFormattedString(@StringRes resId: Int, arg1: Any, arg2: Any): String

    /** Get boolean resource  */
    fun getBoolean(@BoolRes resId: Int): Boolean

    /** Get array of strings  */
    fun getStringsList(@ArrayRes resId: Int) : List<String>

    /** Get current locale */
    fun getLocale() : String

    /** Get dimension value in [px]  */
    fun getDimension(@DimenRes resId: Int) : Float

    /** Get animation */
    fun getAnimation(@AnimRes resId: Int) : Animation

    /** Get fraction */
    fun getFraction(@FractionRes resId: Int, base: Int) : Float
}
