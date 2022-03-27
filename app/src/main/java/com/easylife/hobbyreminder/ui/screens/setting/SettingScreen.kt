package com.easylife.hobbyreminder.ui.screens.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.easylife.hobbyreminder.base.BaseScreen
import com.easylife.hobbyreminder.ui.theme.Black
import com.easylife.hobbyreminder.ui.theme.White

class SettingScreen: BaseScreen<SettingViewModel>() {
    @Composable
    override fun Content() {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "Setting", color = Black)

        }
    }
}