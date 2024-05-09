package com.ilesha23.habittracker.ui.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilesha23.habittracker.data.model.HabitItem
import com.ilesha23.habittracker.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val list = MutableStateFlow<List<HabitItem>>(emptyList())

    init {
        viewModelScope.launch {
            list.update {
                repository.getAll()
            }
        }
    }

    fun insert() {

    }

}