package com.example.myweatherapp

import java.text.SimpleDateFormat
import java.util.*

private val calendar = Calendar.getInstance()

fun getDateFromStamp(stamp : Long?) = getFromStamp(stamp,"dd:MM:yyyy")

fun getDayOfWeekFromStamp(stamp: Long?) = getFromStamp(stamp,"E")

fun getDayAndDateFromStamp(stamp: Long?) = getFromStamp(stamp,"EEEE, d MMMM")

fun getDayOfWeekNumber(date : Long) : Int {
    calendar.time = Date(date * 1000)
    return calendar.get(Calendar.DAY_OF_WEEK)
}

private fun getFromStamp(stamp: Long? ,pattern: String) : String {
    if (stamp==null) {
        return ""
    }
    val date = Date(stamp * 1000)
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getDefault()

    return simpleDateFormat.format(date)
}