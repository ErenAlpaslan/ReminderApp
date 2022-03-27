package com.easylife.hobbyreminder.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.easylife.hobbyreminder.R

sealed class Screen(val route: String, val icon: ImageVector? = null, @StringRes val label: Int = R.string.screen_deafult_name) {
    object Splash: Screen("splash")
    object NewReminder: Screen("new")
    object Dashboard: Screen("main")
    object Home: Screen("home", Icons.Rounded.Home, R.string.screen_home)
    object Detail: Screen("detail/{reminderId}")
    object Setting: Screen("setting", Icons.Rounded.Settings, R.string.screen_setting)
}
