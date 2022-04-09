package com.easylife.hobbyreminder.entity

import androidx.annotation.StringRes

data class Day(
    val day: Int,
    @StringRes val shortCode: Int,
    var selected: Boolean
)
