package com.ilesha23.habittracker.ui.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilesha23.habittracker.data.model.HabitItem
import com.ilesha23.habittracker.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val activeList = MutableStateFlow<List<HabitItem>>(emptyList())
    val archiveList = MutableStateFlow<List<HabitItem>>(emptyList())
    val type = MutableStateFlow(true)
    val name = MutableStateFlow("")
    val dateStart = MutableStateFlow(System.currentTimeMillis())
    val dateFinish = MutableStateFlow(System.currentTimeMillis())

    val dateStartFormatted: MutableStateFlow<String>
        get() = MutableStateFlow(
            SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            ).format(dateStart.value)
        )

    val dateFinishFormatted: MutableStateFlow<String>
        get() = MutableStateFlow(
            SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            ).format(dateFinish.value)
        )

    init {
        updateLists()
    }

    private fun updateLists() {
        viewModelScope.launch {
            val list = repository.getAll()

            activeList.update {
                list.filter {
                    !it.isArchive
                }
            }
            archiveList.update {
                list.filter {
                    it.isArchive
                }
            }
            Log.d("LOG_TAG", archiveList.value.toString())
        }
    }

    fun insert(type: Boolean, name: String, dateStart: Long, dateFinish: Long) {
        this.type.update {
            type
        }
        this.name.update {
            name
        }
        this.dateStart.update {
            dateStart
        }
        this.dateFinish.update {
            dateFinish
        }
        val item = HabitItem(
            name = this.name.value,
            dateStarted = this.dateStart.value,
            dateCutOff = this.dateFinish.value,
            isPositive = this.type.value
        )
        viewModelScope.launch {
            repository.insert(item)
            updateLists()
        }
    }

    fun updateDateStart(time: Long) {
        dateStart.update {
            time
        }
        if (dateFinish.value < dateStart.value) {
            dateFinish.update {
                dateStart.value
            }
        }
    }

    fun updateDateFinish(time: Long) {
        dateFinish.update {
            time
        }
        if (dateFinish.value < dateStart.value) {
            dateStart.update {
                dateFinish.value
            }
        }
    }

}