package com.example.daykeepercompose.ui.home

import org.junit.Test
import org.junit.Assert.*

class TimeValidationUnitTest {

    @Test
    fun timeValidation_correctHourRange() {
        val validHours = listOf("0", "1", "12", "23")
        validHours.forEach { hour ->
            val value = hour.toIntOrNull()
            assertTrue("Hour $hour should be valid", value != null && value <= 23)
        }
    }

    @Test
    fun timeValidation_correctMinuteRange() {
        val validMinutes = listOf("0", "1", "30", "59")
        validMinutes.forEach { minute ->
            val value = minute.toIntOrNull()
            assertTrue("Minute $minute should be valid", value != null && value <= 59)
        }
    }

    @Test
    fun durationCalculation_positiveResult() {
        val startHour = 12
        val startMinute = 30
        val endHour = 14
        val endMinute = 45

        val startTotal = startHour * 60 + startMinute
        val endTotal = endHour * 60 + endMinute
        val duration = endTotal - startTotal

        assertEquals(135, duration)
    }

    @Test
    fun durationCalculation_sameDay() {
        val startTotal = 9 * 60 + 0
        val endTotal = 11 * 60 + 0
        val duration = endTotal - startTotal

        assertEquals(120, duration)
    }

    @Test
    fun timeInput_numberValidation() {
        val inputs = listOf("12", "00", "59")
        inputs.forEach { input ->
            val allDigits = input.all { it.isDigit() }
            assertTrue("Input '$input' should be all digits", allDigits)
        }
    }
}