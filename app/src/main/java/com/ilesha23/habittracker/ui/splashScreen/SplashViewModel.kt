package com.ilesha23.habittracker.ui.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilesha23.habittracker.ui.common.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: Session
) : ViewModel() {

    val isUserAccepted = MutableStateFlow<Boolean?>(null)

    init {
        viewModelScope.launch {
            delay(1000)
            dataStore.getIsUserAccepted()
                .collectLatest { result ->
                    isUserAccepted.update {
                        result
                    }
                }
        }
    }

}