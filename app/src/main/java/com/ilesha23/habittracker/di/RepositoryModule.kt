package com.ilesha23.habittracker.di

import com.ilesha23.habittracker.data.db.HabitDao
import com.ilesha23.habittracker.data.repository.Repository
import com.ilesha23.habittracker.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(dao: HabitDao): Repository {
        return RepositoryImpl(dao)
    }

}