package com.alshevelev.alphapass.dto.entities

/** */
data class Account(
    var id: Long = 0,

    var accountName: String?,

    var userName: String,

    var password: String?,

    var url: String?,

    var notes: String?
)