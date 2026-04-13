package com.example.daykeepercompose.domain.usecase

import com.example.daykeepercompose.domain.model.Task
import java.util.Date

interface SaveTaskUseCase {
    suspend operator fun invoke(date: Date, task: Task)
}