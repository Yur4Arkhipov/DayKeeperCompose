package com.example.daykeepercompose.domain.usecase

import kotlinx.coroutines.flow.StateFlow
import java.util.Date

interface ObserveSelectedDateUseCase  {
    operator fun invoke() : StateFlow<Date>
}