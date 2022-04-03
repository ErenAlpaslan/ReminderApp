package com.easylife.hobbyreminder.common.persistence

import androidx.room.Room
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single { Gson() }
    single { get<AppDatabase>().reminderDao() }
    single { get<AppDatabase>().themeDao() }
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "Reminder.db")
            .fallbackToDestructiveMigration()
            .addCallback(RoomCallback)
            //.addTypeConverter(get<RecordListTypeConverter>())
            .build()
    }
}