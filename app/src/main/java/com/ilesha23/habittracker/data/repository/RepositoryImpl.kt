package com.ilesha23.habittracker.data.repository

import com.ilesha23.habittracker.data.db.HabitDao
import com.ilesha23.habittracker.data.model.HabitItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: HabitDao
) : Repository {
    override val list = MutableStateFlow<List<HabitItem>>(emptyList())

    override suspend fun insert(item: HabitItem) {
        withContext(Dispatchers.IO) {
            db.insert(item)
        }
    }

    override suspend fun getAll(): List<HabitItem> {
        return withContext(Dispatchers.IO) {
            db.getAll()
        }
    }
}