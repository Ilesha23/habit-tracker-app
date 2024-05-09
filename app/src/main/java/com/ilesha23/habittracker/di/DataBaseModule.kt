package com.ilesha23.habittracker.di

import android.content.Context
import androidx.room.Room
import com.ilesha23.habittracker.data.db.DataBase
import com.ilesha23.habittracker.data.db.HabitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            "db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: DataBase): HabitDao {
        return db.habitDao()
    }

}