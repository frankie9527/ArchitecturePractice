package com.sixth.space.uitls

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * @author: Frankie
 * @Date: 2024/3/7
 * @Description:
 */
fun Long.getTime2String(): String {
    val dateFormat = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault())
    return dateFormat.format(Date(this))
}

fun Int.durationToStr(): String {
    val seconds = this % 60
    val minute = this / 60
    return if (seconds < 10) {
        if (minute < 10) "0$minute:0$seconds"
        else "$minute:0$seconds"
    } else {
        if (minute < 10) "0$minute:$seconds"
        else "$minute:$seconds"
    }
}
