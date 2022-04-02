package com.easylife.hobbyreminder.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ThemeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)
