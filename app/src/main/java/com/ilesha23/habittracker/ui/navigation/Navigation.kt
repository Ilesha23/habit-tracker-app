package com.ilesha23.habittracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Onboarding.route) {

        }

        composable(Screen.Onboarding.route) {

        }

        composable(Screen.Main.route) {

        }

        composable(Screen.Settings.route) {

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

fun NavController.navigateWithPopUp(
    popUpTo: String,
    route: String = popUpTo
) {
    navigate(route = route) {
        popUpTo(popUpTo) {
            inclusive = popUpTo == route
        }
    }
}