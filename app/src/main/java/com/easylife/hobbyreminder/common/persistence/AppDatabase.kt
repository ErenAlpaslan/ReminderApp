package com.easylife.hobbyreminder.common.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.easylife.hobbyreminder.BuildConfig
import com.easylife.hobbyreminder.common.persistence.converters.DayTypeConverter
import com.easylife.hobbyreminder.common.persistence.converters.TimeTypeConverter
import com.easylife.hobbyreminder.common.persistence.dao.ReminderDao
import com.easylife.hobbyreminder.common.persistence.dao.ThemeDao
import com.easylife.hobbyreminder.entity.ThemeEntity

@Database(
    entities = [
        ThemeEntity::class
    ],
    version = BuildConfig.VERSION_CODE
)
@TypeConverters(
    TimeTypeConverter::class,
    DayTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun reminderDao(): ReminderDao

    abstract fun themeDao(): ThemeDao

}