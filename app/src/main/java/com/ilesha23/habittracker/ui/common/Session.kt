package com.ilesha23.habittracker.ui.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Session @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        const val DATA = "Data"
        const val IS_ACCEPTED = "isAccepted"
        val isAccepted = booleanPreferencesKey(IS_ACCEPTED)
    }

    suspend fun setUserAccepted() {
        dataStore.edit { preference ->
            preference[isAccepted] = true
        }
    }

    fun getIsUserAccepted(): Flow<Boolean> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }.map { preferences ->
                preferences[isAccepted] ?: false
            }
    }

}