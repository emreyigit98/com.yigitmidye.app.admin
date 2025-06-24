package com.yigitmidye.app.admin.util

import com.google.firebase.Timestamp
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date


fun setPairTimesTamp(): Pair<Timestamp, Timestamp> {

    val zoneId = ZoneId.of("Europe/Istanbul")
    val today = LocalDate.now(zoneId)
    val startOfDay = today.atStartOfDay(zoneId).toInstant()
    val endOfDay = today.atTime(LocalTime.MAX).atZone(zoneId).toInstant()

    return Pair(Timestamp(Date.from(startOfDay)), Timestamp(Date.from(endOfDay)))
}
