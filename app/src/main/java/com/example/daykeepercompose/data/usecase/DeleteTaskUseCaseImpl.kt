package com.example.daykeepercompose.data.usecase

import com.example.daykeepercompose.domain.repository.TaskRepository
import com.example.daykeepercompose.domain.usecase.DeleteTaskUseCase
import javax.inject.Inject

class DeleteTaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
) : DeleteTaskUseCase {

    override suspend fun invoke(taskId: Int) {
        taskRepository.deleteTask(taskId)
    }
}