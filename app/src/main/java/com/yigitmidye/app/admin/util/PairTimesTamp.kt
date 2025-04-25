package com.yigitmidye.app.admin.util

import com.google.firebase.Timestamp
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

/*
fun getPairTimesTamp(): Pair<Timestamp, Timestamp> {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val startOfDay = calendar.time // Günün başlangıcı (00:00:00)

    val endOfDay = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
        time = startOfDay
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
    }.time // Günün bitişi (23:59:59.999)

    return Pair(Timestamp(startOfDay), Timestamp(endOfDay))
}

 */

fun setPairTimesTamp(): Pair<Timestamp, Timestamp> {

    val zoneId = ZoneId.of("Europe/Istanbul")
    val today = LocalDate.now(zoneId)
    val startOfDay = today.atStartOfDay(zoneId).toInstant()
    val endOfDay = today.atTime(LocalTime.MAX).atZone(zoneId).toInstant()

    return Pair(Timestamp(Date.from(startOfDay)), Timestamp(Date.from(endOfDay)))
}
