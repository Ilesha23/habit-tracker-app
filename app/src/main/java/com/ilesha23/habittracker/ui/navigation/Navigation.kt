package com.ilesha23.habittracker.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilesha23.habittracker.ui.mainScreen.MainScreen
import com.ilesha23.habittracker.ui.onboardingScreen.OnBoardingScreen
import com.ilesha23.habittracker.ui.settingsScreen.SettingsScreen
import com.ilesha23.habittracker.ui.splashScreen.SplashScreen

@Composable
fun Navigation(
    onClose: () -> Unit = {}
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(
                onOnboardingNavigate = {
                    navController.performIfCurrentDestinationDoesntMatch(Screen.Onboarding.route) {
                        navigate(Screen.Onboarding.route)
                    }
                },
                onMainNavigate = {
                    navController.performIfCurrentDestinationDoesntMatch(Screen.Main.route) {
                        navigate(Screen.Main.route)
                    }
                    Log.d("LOG_TAG", navController.currentBackStack.value.toString())
                }
            )
        }

        composable(Screen.Onboarding.route) {
            OnBoardingScreen(
                onNavigate = {
                    navController.performIfCurrentDestinationDoesntMatch(Screen.Main.route) {
                        navigate(Screen.Main.route)
                    }
                }
            )
        }

        composable(Screen.Main.route) {
            MainScreen(
                onSettingsClick = {
                    navController.performIfCurrentDestinationDoesntMatch(Screen.Settings.route) {
                        navigate(Screen.Settings.route)
                    }
                },
                onBacClick = {
                    onClose()
                    Log.d("LOG_TAG", navController.currentBackStack.value.toString())
                }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onBackClick = {
                    navController.performIfCurrentDestinationDoesntMatch(Screen.Main.route) {
                        navigateUp()
                    }
                }
            )
        }

    }
}

fun NavController.performIfCurrentDestinationDoesntMatch(
    secondDestination: String,
    action: NavController.() -> Unit
) {
    if (currentDestination?.route != secondDestination) {
        action()
    }
}