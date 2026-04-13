package com.example.daykeepercompose.core.di

import android.content.Context
import androidx.room.Room
import com.example.daykeepercompose.data.local.TaskDao
import com.example.daykeepercompose.data.local.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskDatabase {
        val db = Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "db"
        )
            .fallbackToDestructiveMigration(true)
            .build()
        return db
    }

    @Provides
    fun provideTaskDao(db: TaskDatabase): TaskDao = db.taskDao()
}