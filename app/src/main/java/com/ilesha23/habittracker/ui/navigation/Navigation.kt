package com.ilesha23.habittracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilesha23.habittracker.ui.mainScreen.MainScreen
import com.ilesha23.habittracker.ui.onboardingScreen.OnBoardingScreen
import com.ilesha23.habittracker.ui.splashScreen.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        // TODO: remove from stack
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
            MainScreen()
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