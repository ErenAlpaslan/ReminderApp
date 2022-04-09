package com.easylife.hobbyreminder.ui.widget

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easylife.hobbyreminder.R
import com.easylife.hobbyreminder.ui.theme.White

@Composable
fun ReminderDialog(
    title: String,
    modifier: Modifier = Modifier,
    dialogState: MutableState<Boolean>
){
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.background(White)
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
            DaySelector()
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .background(Color.LightGray)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            HoursPicker(
                hours = {

                },
                minutes = {

                },
                timePeriod = {

                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .background(Color.LightGray)
                    .fillMaxWidth()
            )
            RepeatSelector()
            Spacer(modifier = Modifier.height(30.dp))
            ActionsRow(
                onDone = {

                },
                onCancel = {

                }
            )
        }
    }
}

@Composable
fun RepeatSelector() {
    val selected = remember {
        mutableStateOf(0)
    }
    Column() {
        Text(
            text = stringResource(id = R.string.reminder_dialog_repeat_title),
            style = MaterialTheme.typography.subtitle1
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow {
            itemsIndexed(listOf(
                R.string.reminder_dialog_repeat_every_week,
                R.string.reminder_dialog_repeat_every_month,
                R.string.reminder_dialog_repeat_off
            )) { pos, nameId ->
                Row( modifier = Modifier.clickable {
                    selected.value = pos
                }) {
                    RadioButton(
                        selected = pos == selected.value,
                        onClick = {
                        },
                    )
                    Text(
                        text = stringResource(id = nameId),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ActionsRow(
    onDone: () -> Unit,
    onCancel: () -> Unit
) {

}