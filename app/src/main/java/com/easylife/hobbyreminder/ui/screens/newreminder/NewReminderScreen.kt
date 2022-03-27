package com.easylife.hobbyreminder.ui.screens.newreminder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easylife.hobbyreminder.base.BaseScreen
import com.easylife.hobbyreminder.ui.theme.Orange

class NewReminderScreen: BaseScreen<NewReminderViewModel>() {

    @Composable
    override fun Content() {
        Box(modifier = Modifier.background(Orange)
            .fillMaxSize()) {
        }
    }
}