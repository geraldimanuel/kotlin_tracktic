package com.example.kotlin_tracktic.util

import java.util.Date

data class TransactionData(
    val nominal: Int,
    val category: String,
    val date: Date,
    val description: String,
    val type: String
)
