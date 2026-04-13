package com.example.daykeepercompose.ui.detail

import org.junit.Test
import org.junit.Assert.*

class TimeFormattingUnitTest {

    @Test
    fun formatTimeFromMinutes_correctlyFormatsTime() {
        val result1 = formatTimeFromMinutes(0)
        assertEquals("00:00", result1)

        val result2 = formatTimeFromMinutes(60)
        assertEquals("01:00", result2)

        val result3 = formatTimeFromMinutes(90)
        assertEquals("01:30", result3)

        val result4 = formatTimeFromMinutes(720)
        assertEquals("12:00", result4)

        val result5 = formatTimeFromMinutes(1439)
        assertEquals("23:59", result5)
    }

    @Test
    fun getDurationString_correctlyFormatsDuration() {
        val result1 = getDurationString(30)
        assertEquals("30 мин", result1)

        val result2 = getDurationString(60)
        assertEquals("1 ч", result2)

        val result3 = getDurationString(90)
        assertEquals("1 ч 30 мин", result3)

        val result4 = getDurationString(120)
        assertEquals("2 ч", result4)
    }
}