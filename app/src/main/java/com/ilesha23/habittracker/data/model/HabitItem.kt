package com.ilesha23.habittracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Entity
data class HabitItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dateStarted: Long,
    val dateCutOff: Long,
    val isPositive: Boolean = true
) {
    val formattedStartedDate: String
        get() = SimpleDateFormat("dd\nMMM\nyyyy", Locale.getDefault()).format(dateStarted)

    val formattedCutoffDate: String
        get() = SimpleDateFormat("dd\nMMM\nyyyy", Locale.getDefault()).format(dateCutOff)

    val daysPassed: Long
        get() = ((Calendar.getInstance().time.time - dateStarted) / (1000 * 60 * 60 * 24))

    val isArchive: Boolean
        get() = Calendar.getInstance().time.after(Date(dateCutOff))

}
