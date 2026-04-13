package com.example.daykeepercompose.data.usecase

import com.example.daykeepercompose.domain.repository.SelectedDateHolder
import com.example.daykeepercompose.domain.usecase.ObserveSelectedDateUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

class ObserveSelectedDateUseCaseImpl @Inject constructor(
    private val repository: SelectedDateHolder
) : ObserveSelectedDateUseCase {
    override operator fun invoke(): StateFlow<Date> = repository.selectedDate
}