package com.example.myweatherapp

import android.icu.util.LocaleData
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*
import java.util.regex.Pattern

fun getDateFromStamp(stamp : Long?) = getFromStamp(stamp,"dd:MM:yyyy")

fun getDayOfWeekFromStamp(stamp: Long?) = getFromStamp(stamp,"E")

private fun getFromStamp(stamp: Long? ,pattern: String) : String {
    if (stamp==null) {
        return ""
    }
    val date = Date(stamp * 1000)
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getDefault()

    return simpleDateFormat.format(date)
}