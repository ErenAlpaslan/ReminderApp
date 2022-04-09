package com.easylife.hobbyreminder.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Reminders")
data class ReminderConfig(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String?,
    val days: List<Day>,
    val time: Time,
    val repeatOption: Int
)
