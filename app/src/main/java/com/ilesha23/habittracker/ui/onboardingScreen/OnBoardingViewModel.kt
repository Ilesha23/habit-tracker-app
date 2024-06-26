package com.ilesha23.habittracker.ui.onboardingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilesha23.habittracker.data.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val dataStore: DataStoreManager
) : ViewModel() {

    fun setUserAccepted() {
        viewModelScope.launch {
            dataStore.setUserAccepted()
        }
    }

}