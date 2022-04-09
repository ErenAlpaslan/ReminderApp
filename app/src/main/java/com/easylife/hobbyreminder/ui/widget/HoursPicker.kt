package com.easylife.hobbyreminder.ui.widget

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easylife.hobbyreminder.ui.theme.Gray
import com.easylife.hobbyreminder.ui.theme.LightBlue
import com.easylife.hobbyreminder.ui.theme.Orange
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HoursPicker(
    hours: (Int) -> Unit,
    minutes: (Int) -> Unit,
    timePeriod: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        HoursSlider{
            hours(it)
        }
        Spacer(modifier = Modifier.width(20.dp))
        MinutesSlider {
            minutes(it)
        }
        Spacer(modifier = Modifier.width(10.dp))
        TimePeriodSelection {
            timePeriod(it)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HoursSlider(
    hours: (Int) -> Unit
) {
    val pagerState = rememberPagerState()

    VerticalPager(
        count = 12,
        modifier = Modifier
            .width(30.dp)
            .height(100.dp),
        contentPadding = PaddingValues(vertical = 30.dp),
        state = pagerState
    ) { page ->
        hours(pagerState.currentPage + 1)
        Text(
            text = String.format("%02d", (page + 1)),
            style = MaterialTheme.typography.h2,
            color = if (page == pagerState.currentPage) Orange else Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MinutesSlider(
    minutes: (Int) -> Unit
) {
    val pagerState = rememberPagerState()

    VerticalPager(
        count = 60,
        modifier = Modifier
            .width(30.dp)
            .height(100.dp),
        contentPadding = PaddingValues(vertical = 30.dp),
        state = pagerState
    ) { page ->
        minutes(pagerState.currentPage)
        Text(
            text = String.format("%02d", page),
            style = MaterialTheme.typography.h2,
            color = if (page == pagerState.currentPage) Orange else Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
fun TimePeriodSelection(
    type: (Int) -> Unit
) {
    val selected = remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.wrapContentWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(
            onClick = {
                selected.value = 0
                type(0)
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Orange
            )
        ) {
            Text(
                text = "AM",
                color = if (selected.value == 0) Orange else Gray
            )
        }
        TextButton(
            onClick = {
                selected.value = 1
                type(1)
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Orange
            )
        ) {
            Text(
                text = "PM",
                color = if (selected.value == 1) Orange else Gray
            )
        }
    }
}