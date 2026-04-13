package com.example.daykeepercompose.ui.model

import org.junit.Test
import org.junit.Assert.*

class TaskUiUnitTest {

    @Test
    fun taskUi_creation() {
        val taskUi = TaskUi(
            id = 1,
            startMinute = 540,  // 09:00
            durationMinutes = 120,  // 2 hours
            name = "smth",
            description = "some"
        )

        assertEquals(1, taskUi.id)
        assertEquals(540, taskUi.startMinute)
        assertEquals(120, taskUi.durationMinutes)
        assertEquals("smth", taskUi.name)
        assertEquals("some", taskUi.description)
    }

    @Test
    fun taskUi_endTimeCalculation() {
        val taskUi = TaskUi(
            id = 1,
            startMinute = 480,
            durationMinutes = 90,
            name = "Test",
            description = "test"
        )

        val endMinute = taskUi.startMinute + taskUi.durationMinutes
        assertEquals(570, endMinute)
    }
}