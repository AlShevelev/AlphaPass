package com.alshevelev.alphapass.presentation.screens.createPasswordScreen.viewState

import android.support.annotation.StringRes

/** Show some error  */
data class ErrorViewState(
    @StringRes val errorResourceId: Int
): CreatePasswordScreenViewState