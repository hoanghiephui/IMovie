package com.redvelvet.ui.screens.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.redvelvet.ui.navigation.MovieDestination


fun NavGraphBuilder.onBoardingRoute(navController: NavController) {
    composable(route = MovieDestination.OnBoarding.route) {
        OnBoardingScreen(navController = navController)
    }
}

fun NavController.navToOnBoarding() {
    navigate(MovieDestination.OnBoarding.route)
}