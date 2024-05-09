package com.ilesha23.habittracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.ilesha23.habittracker.ui.navigation.Navigation
import com.ilesha23.habittracker.ui.theme.HabitTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            HabitTrackerTheme {
                Navigation(
                    onClose = {
                        finish() // yes, maybe that's a bad solution, but simple popupto navigation didn't work for me and i had deadline :(
                    }
                )
            }
        }
    }
}