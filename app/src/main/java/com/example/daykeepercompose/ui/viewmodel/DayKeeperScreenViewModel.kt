package com.example.daykeepercompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daykeepercompose.domain.model.Task
import com.example.daykeepercompose.domain.model.toUi
import com.example.daykeepercompose.domain.usecase.DeleteTaskUseCase
import com.example.daykeepercompose.domain.usecase.GetDayDataUseCase
import com.example.daykeepercompose.domain.usecase.ObserveSelectedDateUseCase
import com.example.daykeepercompose.domain.usecase.SaveTaskUseCase
import com.example.daykeepercompose.domain.usecase.SetSelectedDateUseCase
import com.example.daykeepercompose.ui.model.TaskUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

data class DayKeeperUiState(
    val selectedDate: Date = Date(),
    val tasks: List<TaskUi> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class DayKeeperScreenViewModel @Inject constructor(
    observeSelectedDateUseCase: ObserveSelectedDateUseCase,
    private val getDayDataUseCase: GetDayDataUseCase,
    private val setSelectedDateUseCase: SetSelectedDateUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val zoneId: ZoneId
) : ViewModel() {

    private val selectedDate = observeSelectedDateUseCase()

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<DayKeeperUiState> =
        selectedDate
            .flatMapLatest { selectedDate ->
                getDayDataUseCase(selectedDate)
                    .map { dayData ->
                        DayKeeperUiState(
                            selectedDate = selectedDate,
                            tasks = dayData.map { it.toUi(zoneId = zoneId) },
                            isLoading = false
                        )
                    }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                DayKeeperUiState(isLoading = true)
            )

    fun onDateSelected(date: Date) {
        viewModelScope.launch {
            setSelectedDateUseCase(date)
        }
    }

    fun saveTask(
        name: String,
        description: String,
        startHour: Int,
        startMinute: Int,
        durationMinutes: Int
    ) {
        val date = selectedDate.value

        val startDateTime = date
            .toInstant()
            .atZone(zoneId)
            .toLocalDate()
            .atTime(startHour, startMinute)

        val startMillis = startDateTime
            .atZone(zoneId)
            .toInstant()
            .toEpochMilli()

        val endMillis = startDateTime
            .plusMinutes(durationMinutes.toLong())
            .atZone(zoneId)
            .toInstant()
            .toEpochMilli()

        val task = Task(
            id = 0,
            dateStart = startMillis,
            dateFinish = endMillis,
            name = name,
            description = description,
        )

        viewModelScope.launch {
            saveTaskUseCase(date = date, task = task)
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            deleteTaskUseCase(taskId)
        }
    }
}