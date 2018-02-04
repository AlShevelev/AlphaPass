package com.alshevelev.alphapass.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.alshevelev.alphapass.core.dependencyInjection.coreModule
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware

/** */
class App: Application(), KodeinAware {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
        get() = field !!

        @SuppressLint("StaticFieldLeak")
        var injections: Kodein? = null
        get() = field !!
    }

    /** Kodein DI object */
    override val kodein: Kodein = Kodein {
        import(coreModule)
    }

    /** */
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        injections = kodein
    }
}