package com.alshevelev.alphapass.presentation.screens.rootScreen

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alshevelev.alphapass.R
import com.alshevelev.alphapass.app.App
import com.alshevelev.alphapass.core.storages.keyValue.KeyValueStorageFacadeInterface
import com.alshevelev.alphapass.presentation.screens.createPasswordScreen.view.CreatePasswordScreenActivity
import com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.view.EnterPasswordScreenActivity
import com.github.salomonbrys.kodein.instance

class RootScreenActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_screen)

        val keyValueStorage: KeyValueStorageFacadeInterface = App.injections!!.instance()

        val intent = if(keyValueStorage.isPasswordExists()) {
            Intent(this, EnterPasswordScreenActivity::class.java)
        }
        else {
            Intent(this, CreatePasswordScreenActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
