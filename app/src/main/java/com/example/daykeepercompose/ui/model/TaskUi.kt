package com.example.daykeepercompose.ui.model


data class TaskUi(
    val id: Int,
    val startMinute: Int,
    val durationMinutes: Int,
    val name: String,
    val description: String
)