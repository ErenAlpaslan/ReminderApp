package com.easylife.hobbyreminder.ui.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash")
    object NewReminder: Screen("new")
    object Home: Screen("home")
    object Detail: Screen("detail/{reminderId}")
    object Setting: Screen("setting")
}
