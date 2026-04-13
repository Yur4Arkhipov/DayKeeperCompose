package com.example.daykeepercompose.domain.usecase

interface DeleteTaskUseCase {
    suspend operator fun invoke(taskId: Int)
}