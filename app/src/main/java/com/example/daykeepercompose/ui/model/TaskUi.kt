package com.example.daykeepercompose.ui.model

import androidx.compose.ui.graphics.Color

data class TaskUi(
    val id: Int,
    val startMinute: Int,
    val durationMinutes: Int,
    val name: String,
    val color: Color
)