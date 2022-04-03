package com.easylife.hobbyreminder.entity

import androidx.annotation.ColorRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Themes")
data class ThemeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColorRes val color: Int,
)
