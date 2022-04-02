package com.easylife.hobbyreminder.common.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.easylife.hobbyreminder.BuildConfig
import com.easylife.hobbyreminder.common.persistence.dao.ReminderDao
import com.easylife.hobbyreminder.common.persistence.dao.ThemeDao
import com.easylife.hobbyreminder.entity.ThemeEntity

@Database(
    entities = [
        ThemeEntity::class
    ],
    version = BuildConfig.VERSION_CODE
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun reminderDao(): ReminderDao

    abstract fun themeDao(): ThemeDao

}