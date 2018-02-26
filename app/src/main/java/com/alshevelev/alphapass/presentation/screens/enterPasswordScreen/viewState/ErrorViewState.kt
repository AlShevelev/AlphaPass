package com.alshevelev.alphapass.presentation.screens.enterPasswordScreen.viewState

import android.support.annotation.StringRes

/** Show some error */
data class ErrorViewState(
    @StringRes val errorResourceId: Int
): EnterPasswordScreenViewState