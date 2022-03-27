package com.easylife.hobbyreminder.ui.screens.newreminder

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ColorLens
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easylife.hobbyreminder.R
import com.easylife.hobbyreminder.base.BaseScreen
import com.easylife.hobbyreminder.ui.navigation.Screen
import com.easylife.hobbyreminder.ui.theme.Orange
import com.easylife.hobbyreminder.ui.theme.Purple
import com.easylife.hobbyreminder.ui.theme.White

class NewReminderScreen : BaseScreen<NewReminderViewModel>() {

    @Composable
    override fun Content() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.Home.route) },
                    backgroundColor = Purple,
                    contentColor = White,
                ) {
                    Icon(imageVector = Icons.Rounded.Edit, contentDescription = "Fab Icon")
                }
            },
            content = {
                val title = remember {
                    mutableStateOf("")
                }
                Column (
                    modifier = Modifier.fillMaxWidth()
                        ){
                    Header()
                    OutlinedTextField(
                        value = title.value,
                        onValueChange = {
                            title.value = it
                        },
                        label = { Text("Reminder title") }
                    )
                }
            }
        )
    }

    @Composable
    fun Header() {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.new_reminder_welcome),
                    style = MaterialTheme.typography.body2
                )
                Button(
                    onClick = {
                        /* TODO open theme selection bottom sheet */
                    },
                    modifier = Modifier.padding(8.dp),
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Orange,
                        contentColor = White
                    )
                ) {
                    Icon(imageVector = Icons.Rounded.ColorLens, contentDescription = "icon")
                }
            }
            Text(
                text = stringResource(id = R.string.new_reminder_add_new_reminder),
                style = MaterialTheme.typography.h1
            )
        }
    }
}