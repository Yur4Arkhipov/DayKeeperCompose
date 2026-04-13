package com.example.daykeepercompose.data.usecase

import com.example.daykeepercompose.domain.model.Task
import com.example.daykeepercompose.domain.usecase.GetDayDataUseCase
import com.example.daykeepercompose.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetDayDataUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
) : GetDayDataUseCase {

    override fun invoke(date: Date): Flow<List<Task>> {
        return taskRepository.observeDayData(date)
    }
}