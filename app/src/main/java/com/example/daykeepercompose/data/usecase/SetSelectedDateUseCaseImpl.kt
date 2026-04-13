package com.example.daykeepercompose.data.usecase

import com.example.daykeepercompose.domain.repository.SelectedDateHolder
import com.example.daykeepercompose.domain.usecase.SetSelectedDateUseCase
import jakarta.inject.Inject
import java.util.Date

class SetSelectedDateUseCaseImpl @Inject constructor(
    private val repository: SelectedDateHolder
) : SetSelectedDateUseCase {
    override suspend fun invoke(date: Date) {
        repository.setDate(date)
    }
}