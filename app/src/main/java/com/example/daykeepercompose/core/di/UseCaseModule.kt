package com.example.daykeepercompose.core.di

import com.example.daykeepercompose.data.usecase.GetDayDataUseCaseImpl
import com.example.daykeepercompose.data.usecase.ObserveSelectedDateUseCaseImpl
import com.example.daykeepercompose.data.usecase.SaveTaskUseCaseImpl
import com.example.daykeepercompose.data.usecase.SetSelectedDateUseCaseImpl
import com.example.daykeepercompose.domain.usecase.GetDayDataUseCase
import com.example.daykeepercompose.domain.repository.SelectedDateHolder
import com.example.daykeepercompose.domain.repository.TaskRepository
import com.example.daykeepercompose.domain.usecase.ObserveSelectedDateUseCase
import com.example.daykeepercompose.domain.usecase.SaveTaskUseCase
import com.example.daykeepercompose.domain.usecase.SetSelectedDateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetDayDataUseCase(taskRepository: TaskRepository) : GetDayDataUseCase {
        return GetDayDataUseCaseImpl(taskRepository)
    }

    @Provides
    fun provideObserveDayDataUseCase(selectedDateRepository: SelectedDateHolder) : ObserveSelectedDateUseCase {
        return ObserveSelectedDateUseCaseImpl(selectedDateRepository)
    }

    @Provides
    fun setSelectedDateUseCase(selectedDateRepository: SelectedDateHolder) : SetSelectedDateUseCase {
        return SetSelectedDateUseCaseImpl(selectedDateRepository)
    }

    @Provides
    fun provideSaveTaskUseCase(taskRepository: TaskRepository): SaveTaskUseCase {
        return SaveTaskUseCaseImpl(taskRepository)
    }
}