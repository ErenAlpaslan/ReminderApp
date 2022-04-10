package com.easylife.hobbyreminder.ui.widget

import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.easylife.hobbyreminder.R
import com.easylife.hobbyreminder.entity.ThemeEntity
import com.easylife.hobbyreminder.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ThemeSelectionBottomSheet(
    bottomSheetState: BottomSheetScaffoldState,
    list: List<ThemeEntity>?,
    selectedTheme: (ThemeEntity) -> Unit,
    content: @Composable () -> Unit
){
    val selectedPos = remember {
        mutableStateOf(0)
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetContent = {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(2.dp)
                        .background(Color.LightGray),
                ) {
                }
                Text(
                    text = "Select your theme",
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(top = 30.dp)
                )
                LazyRow(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f),
                    verticalAlignment = Alignment.Bottom
                ) {
                    list?.let {
                        itemsIndexed(it) { index, item ->
                            ThemeCard(
                                pos = index,
                                theme = item,
                                selected = index == selectedPos.value
                            ) { position, theme ->
                                selectedTheme(theme)
                                selectedPos.value = position
                            }
                        }
                    }
                }
            }

        },
        sheetShape = MaterialTheme.shapes.medium,
        sheetBackgroundColor = White,
        sheetPeekHeight = 0.dp,
    ) {
        content()
    }
}

@Composable
fun ThemeCard(
    pos: Int,
    theme: ThemeEntity,
    selected: Boolean,
    onSelected: (pos: Int, theme: ThemeEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                start = 3.dp,
                end = 3.dp,
                bottom = 16.dp
            )
            .fillMaxWidth(0.4f)
            .fillMaxHeight(0.85f),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .clickable { onSelected(pos, theme) }
                .fillMaxSize()
                .border(
                    width = 1.dp,
                    color = if (selected) Orange else Color.Transparent,
                    shape = MaterialTheme.shapes.medium
                ),
        ) {
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .padding(3.dp)
                    .fillMaxSize(),
                shape = MaterialTheme.shapes.medium,
                backgroundColor = colorResource(id = theme.color)
            ) {
            }
        }
    }
}