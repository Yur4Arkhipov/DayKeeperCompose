package com.example.daykeepercompose.domain.repository

import com.example.daykeepercompose.domain.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.Date

interface TaskRepository {
//    suspend fun getTaskById(id: Int): Task?
//    suspend fun insertTask(task: Task): Long
//    suspend fun updateTask(task: Task)
//    suspend fun deleteTask(task: Task)
    fun observeDayData(date: Date): Flow<List<Task>>
    suspend fun insertTask(date: Date, task: Task)
}