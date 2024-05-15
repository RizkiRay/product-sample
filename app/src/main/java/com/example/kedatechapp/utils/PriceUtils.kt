package com.example.kedatechapp.utils

import java.text.DecimalFormat

fun Long.toIndonesiaRupiahFormat(): String {
    val format = DecimalFormat("#,###,###,###")
    return format.format(this)
}