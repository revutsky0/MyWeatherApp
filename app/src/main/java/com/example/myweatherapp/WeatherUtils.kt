package com.example.myweatherapp

import java.text.SimpleDateFormat
import java.util.*

private val calendar = Calendar.getInstance()

fun getDateFromStamp(stamp: Long?) = getFromStamp(stamp, "dd MMMM")

fun getDayOfWeekFromStamp(stamp: Long?) = getFromStamp(stamp, "E")

fun getDayAndDateFromStamp(stamp: Long?) = getFromStamp(stamp, "EEEE, d MMMM")

private fun getFromStamp(stamp: Long?, pattern: String): String {
    if (stamp == null) {
        return ""
    }
    val date = Date(stamp * 1000)
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getDefault()

    return simpleDateFormat.format(date)
}