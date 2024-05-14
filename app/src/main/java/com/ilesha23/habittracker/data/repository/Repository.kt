package com.ilesha23.habittracker.data.repository

import com.ilesha23.habittracker.data.model.HabitItem
import kotlinx.coroutines.flow.Flow

interface Repository {
    val list: Flow<List<HabitItem>>
    suspend fun insert(item: HabitItem)
}