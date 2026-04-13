package com.example.daykeepercompose.domain.model

import com.example.daykeepercompose.ui.model.TaskUi
import java.time.Duration
import java.time.Instant
import java.time.ZoneId

data class Task(
    val id: Int,
    val dateStart: Long,
    val dateFinish: Long,
    val name: String,
    val description: String
)

fun Task.toUi(zoneId: ZoneId): TaskUi {

    val start = Instant.ofEpochMilli(dateStart).atZone(zoneId)
    val end = Instant.ofEpochMilli(dateFinish).atZone(zoneId)

    val startMinute = start.hour * 60 + start.minute

    val durationMinutes = Duration.between(start, end).toMinutes().toInt()

    return TaskUi(
        id = id,
        startMinute = startMinute,
        durationMinutes = durationMinutes,
        name = name,
        description = description
    )
}