package com.alshevelev.alphapass.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/** */
class App: Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
        get() = field !!
    }

    /** */
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}