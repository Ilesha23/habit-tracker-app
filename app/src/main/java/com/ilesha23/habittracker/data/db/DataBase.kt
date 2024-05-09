package com.ilesha23.habittracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilesha23.habittracker.data.model.HabitItem

@Database(
    entities = [HabitItem::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    abstract fun habitDao(): HabitDao

}