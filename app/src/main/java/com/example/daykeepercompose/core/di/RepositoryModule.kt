package com.example.daykeepercompose.core.di

import com.example.daykeepercompose.data.repository.SelectedDateHolderImpl
import com.example.daykeepercompose.data.repository.TaskRepositoryImpl
import com.example.daykeepercompose.domain.repository.SelectedDateHolder
import com.example.daykeepercompose.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindTaskRepository(
        impl: TaskRepositoryImpl
    ): TaskRepository

    @Binds
    fun bindSelectedDateHolder(
        impl: SelectedDateHolderImpl
    ): SelectedDateHolder
}