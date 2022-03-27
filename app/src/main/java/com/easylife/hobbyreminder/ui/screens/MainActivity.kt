package com.easylife.hobbyreminder.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.easylife.hobbyreminder.ui.navigation.NavGraph
import com.easylife.hobbyreminder.ui.navigation.Screen
import com.easylife.hobbyreminder.ui.theme.Gray
import com.easylife.hobbyreminder.ui.theme.HobbyReminderTheme
import com.easylife.hobbyreminder.ui.theme.Orange
import com.easylife.hobbyreminder.ui.theme.Purple

class MainActivity : ComponentActivity() {

    private val bottomNav: List<Screen> by lazy {
        listOf(
            Screen.Home,
            Screen.Setting
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HobbyReminderTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        com.easylife.hobbyreminder.ui.widget.BottomNavigation(
                            items = bottomNav,
                            navController = navController,
                            selectedColor = Purple,
                            unselectedColor = Gray,
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background),
                    content = {
                        NavGraph(navController = navController)
                    }
                )
            }
        }

    }
}
