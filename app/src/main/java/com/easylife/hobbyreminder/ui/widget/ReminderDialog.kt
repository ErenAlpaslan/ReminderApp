package com.easylife.hobbyreminder.ui.widget

import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easylife.hobbyreminder.R
import com.easylife.hobbyreminder.entity.Day
import com.easylife.hobbyreminder.entity.ReminderConfig
import com.easylife.hobbyreminder.entity.Time
import com.easylife.hobbyreminder.ui.theme.Gray
import com.easylife.hobbyreminder.ui.theme.Orange
import com.easylife.hobbyreminder.ui.theme.White

@Composable
fun ReminderDialog(
    title: String,
    modifier: Modifier = Modifier,
    dialogState: MutableState<Boolean>,
    reminderConfig: (ReminderConfig) -> Unit
) {
    val context = LocalContext.current

    val hours = remember {
        mutableStateOf(1)
    }

    val minutes = remember {
        mutableStateOf(0)
    }

    val timePeriod = remember {
        mutableStateOf(0)
    }

    val repeat = remember {
        mutableStateOf(2)
    }

    val selectedDays = remember {
        mutableStateOf(listOf<Day>())
    }

    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .background(White)
                .padding(16.dp)
        ) {

            Text(
                text = stringResource(id = R.string.reminder_dialog_title, title),
                style = MaterialTheme.typography.body2
            )
            Text(
                text = title,
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(50.dp))
            DaySelector {
                selectedDays.value = it
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .background(Color.LightGray)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            HoursPicker(
                hours = {
                    hours.value = it
                },
                minutes = {
                    minutes.value = it
                },
                timePeriod = {
                    timePeriod.value = it
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .background(Color.LightGray)
                    .fillMaxWidth()
            )
            RepeatSelector {
                repeat.value = it
            }
            Spacer(modifier = Modifier.height(30.dp))
            ActionsRow(
                onDone = {
                    if (selectedDays.value.isNotEmpty()) {
                        reminderConfig(
                            ReminderConfig(
                                title = title,
                                days = selectedDays.value,
                                time = Time(
                                    hour = hours.value,
                                    minute = minutes.value,
                                    timePeriod = timePeriod.value
                                ),
                                repeatOption = repeat.value
                            )
                        )
                        dialogState.value = false
                    }else {
                        Toast.makeText(context, R.string.reminder_not_selected_day_error_message, Toast.LENGTH_LONG)
                            .show()
                    }
                },
                onCancel = {
                    dialogState.value = false
                }
            )
        }
    }
}

@Composable
fun RepeatSelector(
    onRepeatSelected: (Int) -> Unit
) {
    val selected = remember {
        mutableStateOf(2)
    }
    onRepeatSelected(selected.value)
    Column() {
        Text(
            text = stringResource(id = R.string.reminder_dialog_repeat_title),
            style = MaterialTheme.typography.body1,
            color = Gray
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.clickable {
                    selected.value = 0
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected.value == 0, onClick = {
                        selected.value = 0
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Orange,
                        unselectedColor = Gray
                    )
                )
                Text(
                    text = stringResource(id = R.string.reminder_dialog_repeat_every_week),
                    modifier = Modifier.padding(start = 3.dp),
                    color = if (selected.value == 0) Orange else Gray
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Row(
                modifier = Modifier.clickable {
                    selected.value = 1
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected.value == 1, onClick = {
                        selected.value = 1
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Orange,
                        unselectedColor = Gray
                    )
                )
                Text(
                    text = stringResource(id = R.string.reminder_dialog_repeat_every_month),
                    modifier = Modifier.padding(start = 3.dp),
                    color = if (selected.value == 1) Orange else Gray
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.clickable {
                    selected.value = 2
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected.value == 2, onClick = {
                        selected.value = 2
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Orange,
                        unselectedColor = Gray
                    )
                )
                Text(
                    text = stringResource(id = R.string.reminder_dialog_repeat_off),
                    modifier = Modifier.padding(start = 3.dp),
                    color = if (selected.value == 2) Orange else Gray
                )
            }
        }
    }
}

@Composable
fun ActionsRow(
    onDone: () -> Unit,
    onCancel: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(
            onClick = { onCancel() },
        ) {
            Text(
                text = stringResource(id = R.string.button_cancel),
                color = Orange
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        TextButton(
            onClick = {
                onDone()
            },
        ) {
            Text(
                text = stringResource(id = R.string.button_ok),
                color = Orange
            )
        }
    }
}