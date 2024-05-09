package com.ilesha23.habittracker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ilesha23.habittracker.data.model.HabitItem

@Dao
interface HabitDao {

    @Query("select * from habititem")
    fun getAll(): List<HabitItem>

    @Insert
    fun insert(item: HabitItem)

}