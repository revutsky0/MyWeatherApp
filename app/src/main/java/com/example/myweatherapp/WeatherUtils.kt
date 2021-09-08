package com.example.myweatherapp

import java.text.SimpleDateFormat
import java.util.*

private val calendar = Calendar.getInstance()
const val DEGREE_STRING = "°"


fun getDateFromStamp(stamp: Long?) = getFromStamp(stamp, "dd MMMM")

fun getDayOfWeekFromStamp(stamp: Long?) = getFromStamp(stamp, "E")

fun getDayAndDateFromStamp(stamp: Long?) = getFromStamp(stamp, "EEEE, d MMMM")

fun getDayOfWeekNumber(date: Long): Int {
    calendar.time = Date(date * 1000)
    return calendar.get(Calendar.DAY_OF_WEEK)
}

fun getDateWithNullTime(date: Long): Long {
    calendar.time = Date(date * 1000L)
    calendar.set(Calendar.MILLISECOND, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    return (calendar.timeInMillis / 1000L)
}

private fun getFromStamp(stamp: Long?, pattern: String): String {
    if (stamp == null) {
        return ""
    }
    val date = Date(stamp * 1000)
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getDefault()

    return simpleDateFormat.format(date)
}