package com.ilesha23.habittracker.data.repository

import com.ilesha23.habittracker.data.db.HabitDao
import com.ilesha23.habittracker.data.model.HabitItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: HabitDao
) : Repository {
    override val list = db.getAll()

    override suspend fun insert(item: HabitItem) {
        withContext(Dispatchers.IO) {
            db.insert(item)
        }
    }

}