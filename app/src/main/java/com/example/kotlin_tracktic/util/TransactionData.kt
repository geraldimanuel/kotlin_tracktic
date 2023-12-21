package com.example.kotlin_tracktic.util

import java.util.Date

data class TransactionData(
    val nominal: Int = 0,
    val category: String = "",
    val date: Date = Date(),
    val description: String = "",
    val type: String = "",
    val id: String = ""
) {
    constructor() : this(0, "", Date(), "", "", "")
}
