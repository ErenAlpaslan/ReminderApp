package com.easylife.hobbyreminder.ui.screens.newreminder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ColorLens
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easylife.hobbyreminder.R
import com.easylife.hobbyreminder.base.BaseScreen
import com.easylife.hobbyreminder.ui.navigation.Screen
import com.easylife.hobbyreminder.ui.theme.*
import com.easylife.hobbyreminder.ui.widget.ThemeSelectionBottomSheet
import kotlinx.coroutines.launch

class NewReminderScreen : BaseScreen<NewReminderViewModel>() {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        val themeSelectionBottomSheetState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
        )
        val coroutineScope = rememberCoroutineScope()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {
                ThemeSelectionBottomSheet(bottomSheetState = themeSelectionBottomSheetState) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .nestedScroll(object: NestedScrollConnection{

                            })

                    ){
                        Spacer(modifier = Modifier.height(24.dp))
                        Header {
                            coroutineScope.launch {
                                if (themeSelectionBottomSheetState.bottomSheetState.isCollapsed) {
                                    themeSelectionBottomSheetState.bottomSheetState.expand()
                                    focusManager.clearFocus()
                                }else {
                                    themeSelectionBottomSheetState.bottomSheetState.collapse()
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(44.dp))
                        ReminderTitle()
                        Spacer(modifier = Modifier.height(10.dp))
                        SettingReminder {
                            /* TODO: Open dialog */
                        }
                        SaveButton()
                    }
                }
            },
        )
    }

    @Composable
    fun Header(onThemeSelection: () -> Unit) {
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
                    style = MaterialTheme.typography.body2,
                    color = Gray
                )
                Button(
                    onClick = {
                              onThemeSelection()
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
    
    @Composable
    fun ReminderTitle() {
        val title = remember {
            mutableStateOf("")
        }
        
        Row(
            modifier = Modifier
                .padding(vertical = 1.dp, horizontal = 16.dp)
                .fillMaxWidth(),
        ) {
            TextField(value = title.value,
                onValueChange = {
                    title.value = it
                    viewModel.onTitleChanged(it)
                },
                modifier = Modifier
                    .background(colorResource(id = android.R.color.transparent))
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = LightBlue,
                    cursorColor = Black,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent),
                textStyle = MaterialTheme.typography.body1,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.new_reminder_title_placeholder),
                        color = Color.Gray,
                        style = MaterialTheme.typography.body1
                    )
                },
                shape = MaterialTheme.shapes.small
            )
        }
    }

    @Composable
    fun SettingReminder(
        onClick: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 1.dp, horizontal = 16.dp)
                .fillMaxWidth()
                .clickable {
                       //TODO: Open reminder dialog
                },
        ) {
            TextField(value = "",
                onValueChange = {
                },
                modifier = Modifier
                    .background(colorResource(id = android.R.color.transparent))
                    .fillMaxWidth()
                    .clickable {
                        onClick()
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = LightBlue,
                    cursorColor = Black,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    trailingIconColor = Gray
                ),
                textStyle = MaterialTheme.typography.body1,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.new_reminder_set_reminder_placeholder),
                        color = Color.Gray,
                        style = MaterialTheme.typography.body1
                    )
                },
                shape = MaterialTheme.shapes.small,
                readOnly = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "icon")
                }
            )
        }
    }

    @Composable
    fun SaveButton() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                onClick = {
                    /* TODO open theme selection bottom sheet */
                },
                modifier = Modifier
                    .padding(bottom = 56.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Purple,
                    contentColor = White,
                    disabledBackgroundColor = LightPurple
                ),
                enabled = false
            ) {
                Text(
                    text = stringResource(id = R.string.new_reminder_save_text),
                    style = MaterialTheme.typography.h2,
                    color = White
                )
            }
        }
    }
}