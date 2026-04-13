package com.example.daykeepercompose.data.usecase

import com.example.daykeepercompose.domain.model.Task
import com.example.daykeepercompose.domain.repository.TaskRepository
import com.example.daykeepercompose.domain.usecase.SaveTaskUseCase
import java.util.Date
import javax.inject.Inject

class SaveTaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
) : SaveTaskUseCase {

    override suspend fun invoke(date: Date, task: Task) {
        taskRepository.insertTask(date, task)
    }
}