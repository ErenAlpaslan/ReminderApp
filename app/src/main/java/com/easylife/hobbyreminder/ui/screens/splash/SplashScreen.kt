package com.easylife.hobbyreminder.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.easylife.hobbyreminder.R
import com.easylife.hobbyreminder.base.BaseScreen
import com.easylife.hobbyreminder.ui.navigation.Screen
import com.easylife.hobbyreminder.ui.theme.Purple
import kotlinx.coroutines.delay

class SplashScreen: BaseScreen<SplashViewModel>() {
    @Composable
    override fun Content() {
        LaunchedEffect(key1 = null) {
            delay(2000L)
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "App Icon"
            )
        }
    }
}