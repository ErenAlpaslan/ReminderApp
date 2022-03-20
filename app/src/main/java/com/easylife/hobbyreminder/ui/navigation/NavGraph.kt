package com.easylife.hobbyreminder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        /*composable(Screen.Splash.route) {
            get<SplashScreen>().Screen(
                viewModel = getViewModel(),
                navController = navController)
        }

        composable(Screen.OnBoarding.route) {
            get<OnBoardingScreen>().Screen(
                viewModel = getViewModel(),
                navController = navController)
        }

        composable(Screen.Dashboard.route) {
            get<DashboardScreen>().Screen(
                viewModel = getViewModel(),
                navController = navController)
        }*/
    }
}