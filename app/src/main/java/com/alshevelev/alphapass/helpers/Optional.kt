package com.alshevelev.alphapass.helpers

/** Wrapper around one value (to solve problem with nullable values in RxJava2) */
data class Optional<out T>(val value: T?)