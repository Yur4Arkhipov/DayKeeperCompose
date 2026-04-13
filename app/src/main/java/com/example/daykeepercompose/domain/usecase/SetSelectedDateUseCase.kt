package com.example.daykeepercompose.domain.usecase

import java.util.Date

interface SetSelectedDateUseCase  {
    suspend operator fun invoke(date: Date)
}