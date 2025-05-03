package com.emrekizil.core.ui

import java.text.NumberFormat
import java.util.Locale

fun formatNumber(number: Int): String {
    return try {
        val formatter = NumberFormat.getNumberInstance(Locale.getDefault())
        formatter.format(number)
    } catch (e: Exception) {
        number.toString()
    }
}