package com.ilesha23.habittracker.data.repository

import com.ilesha23.habittracker.data.model.HabitItem
import kotlinx.coroutines.flow.StateFlow

interface Repository {
    val list: StateFlow<List<HabitItem>>
    suspend fun insert(item: HabitItem)
    suspend fun getAll(): List<HabitItem>
}