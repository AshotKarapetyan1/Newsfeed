package com.grupy.newsfeed.shared.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun convertDateToString(serverDate: String): String?{
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    var date: Date? = null
    try { date = input.parse(serverDate) }
    catch (e: ParseException) { e.printStackTrace() }

    date?.let {
        val sdf = SimpleDateFormat("d MMM yyyy HH:mm", Locale.getDefault())
        return sdf.format(it.time)
    }

    return null
}