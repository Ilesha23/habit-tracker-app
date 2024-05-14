package com.ilesha23.habittracker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ilesha23.habittracker.data.model.HabitItem
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Query("select * from habititem")
    fun getAll(): Flow<List<HabitItem>>

    @Insert
    suspend fun insert(item: HabitItem)

}