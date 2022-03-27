package com.easylife.hobbyreminder.ui.navigation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.easylife.hobbyreminder.ui.screens.home.HomeScreen
import com.easylife.hobbyreminder.ui.screens.newreminder.NewReminderScreen
import com.easylife.hobbyreminder.ui.screens.setting.SettingScreen
import com.easylife.hobbyreminder.ui.screens.splash.SplashScreen
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            get<SplashScreen>().Create(
                viewModel = getViewModel(),
                navController = navController)
        }

        composable(Screen.Home.route) {
            get<HomeScreen>().Create(
                viewModel = getViewModel(),
                navController = navController)
        }

        composable(Screen.Setting.route) {
            get<SettingScreen>().Create(
                viewModel = getViewModel(),
                navController = navController)
        }

        composable(Screen.NewReminder.route) {
            get<NewReminderScreen>().Create(
                viewModel = getViewModel(),
                navController = navController)
        }

        /*composable(Screen.OnBoarding.route) {
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