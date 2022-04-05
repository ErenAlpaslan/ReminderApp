package com.easylife.hobbyreminder.ui.widget

import android.util.Log
import android.view.MotionEvent
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.MotionEventCompat
import androidx.lifecycle.MutableLiveData
import com.easylife.hobbyreminder.R
import com.easylife.hobbyreminder.ui.theme.Black
import com.easylife.hobbyreminder.ui.theme.Orange
import com.easylife.hobbyreminder.ui.theme.TransparentOrange
import com.easylife.hobbyreminder.ui.theme.White

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DaySelector() {
    val startDate = remember {
        mutableStateOf(-1)
    }
    val endDate = remember {
        mutableStateOf(-1)
    }

    val offset = with(LocalDensity.current) {
        15.dp.toPx()
    }

    Column(
        modifier = Modifier
            .height(30.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Box {
            if (startDate.value != -1) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 2.dp),
                    onDraw = {

                        val width = size.width
                        val multiplier = width / 7
                        val height = size.height

                        if (startDate.value <= endDate.value) {
                            val startOffset =
                                Offset(((startDate.value) * multiplier) + offset, height / 2)
                            val endOffset =
                                Offset(((endDate.value + 1) * multiplier) - offset, height / 2)
                            drawLine(
                                color = Orange,
                                start = startOffset,
                                end = endOffset,
                                strokeWidth = height,
                                cap = StrokeCap.Round
                            )
                        } else {
                            if (endDate.value != -1) {
                                val startRounded =
                                    Offset(((startDate.value) * multiplier) + offset, height / 2)
                                val endRounded =
                                    Offset(((startDate.value) * multiplier) + offset, height / 2)
                                drawLine(
                                    color = Orange,
                                    start = startRounded,
                                    end = endRounded,
                                    strokeWidth = height,
                                    cap = StrokeCap.Round
                                )
                                val startReact =
                                    Offset(((startDate.value) * multiplier) + (offset * 2), height / 2)
                                val endReact = Offset((7 * multiplier) + offset, height / 2)
                                drawLine(
                                    color = Orange,
                                    start = startReact,
                                    end = endReact,
                                    strokeWidth = height,
                                    cap = StrokeCap.Square
                                )
                                val startReactLeft = Offset((0 * multiplier) - offset, height / 2)
                                val endReactLeft =
                                    Offset(((endDate.value + 1) * multiplier) - (offset * 2), height / 2)
                                drawLine(
                                    color = Orange,
                                    start = startReactLeft,
                                    end = endReactLeft,
                                    strokeWidth = height,
                                    cap = StrokeCap.Square
                                )
                                val startRoundedLeft =
                                    Offset((endDate.value * multiplier) - offset, height / 2)
                                val endRoundedLeft =
                                    Offset(((endDate.value) * multiplier) + (offset * 2), height / 2)
                                drawLine(
                                    color = Orange,
                                    start = startRoundedLeft,
                                    end = endRoundedLeft,
                                    strokeWidth = height,
                                    cap = StrokeCap.Round
                                )
                            }
                        }
                    }
                )
            }

            LazyRow(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures {
                            val point = clickedPoint(it, size.width)
                            handleTap(
                                point = point,
                                start = startDate.value,
                                end = endDate.value,
                                onStartChanged = { start ->
                                    startDate.value = start
                                },
                                onEndChanged = { end ->
                                    endDate.value = end
                                },
                                onClear = {
                                    startDate.value = -1
                                    endDate.value = -1
                                }
                            )
                        }
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                itemsIndexed(
                    listOf(
                        R.string.reminder_dialog_monday,
                        R.string.reminder_dialog_thursday,
                        R.string.reminder_dialog_wednesday,
                        R.string.reminder_dialog_thuesday,
                        R.string.reminder_dialog_friday,
                        R.string.reminder_dialog_saturday,
                        R.string.reminder_dialog_sunday
                    )
                ) { index, item ->
                    DayItem(
                        selected = inBetween(index, startDate.value, endDate.value, 7),
                        text = item,
                    )
                }
            }
        }


    }
}

@Composable
fun DayItem(
    selected: Boolean,
    @StringRes text: Int
) {
    Column(
        modifier = Modifier
            .width(23.dp)
            .height(23.dp)
            .clip(CircleShape)
            .background(if (selected) Orange else Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.body1,
            color = if (selected) White else Black
        )
    }
}

fun clickedPoint(offset: Offset, width: Int): Int {
    return (7 * offset.x / width).toInt()
}

fun handleTap(
    point: Int,
    start: Int,
    end: Int,
    onStartChanged: (Int) -> Unit,
    onEndChanged: (Int) -> Unit,
    onClear: () -> Unit
) {
    if (start == -1) {
        onStartChanged(point)
    }else if (start != point && end != point) {
        onEndChanged(point)
    }else if (point == start) {
        onClear()
    }
}

fun inBetween(
    current: Int,
    start: Int,
    end: Int,
    max: Int
): Boolean {

    val res = if (current == start || current == end) {
        true
    } else if (end == -1) {
        false
    } else if (end > start) {
        current in start..end
    } else if (end < start) {
        current in start..max || current in 0..end
    } else {
        false
    }

    return res
}