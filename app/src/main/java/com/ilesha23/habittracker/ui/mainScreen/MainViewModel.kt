package com.ilesha23.habittracker.ui.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilesha23.habittracker.data.model.HabitItem
import com.ilesha23.habittracker.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val list =
        repository.list.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val activeList = list.map {
        it.filter {
            !it.isArchive
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val archiveList = list.map {
        it.filter {
            it.isArchive
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
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