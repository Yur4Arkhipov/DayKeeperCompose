package com.example.daykeepercompose.domain.model

import org.junit.Test
import org.junit.Assert.*


class TaskUnitTest {
    @Test
    fun task_durationCalculation() {
        val startTime = 1000L
        val endTime = 5000L
        val task = Task(
            id = 1,
            dateStart = startTime,
            dateFinish = endTime,
            name = "Duration Test",
            description = ""
        )

        val expectedDuration = endTime - startTime
        assertEquals(expectedDuration, task.dateFinish - task.dateStart)
    }
}