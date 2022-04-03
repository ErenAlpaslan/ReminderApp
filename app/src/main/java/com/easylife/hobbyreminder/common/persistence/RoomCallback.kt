package com.easylife.hobbyreminder.common.persistence

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.easylife.hobbyreminder.R
import com.easylife.hobbyreminder.common.persistence.dao.ThemeDao
import com.easylife.hobbyreminder.entity.ThemeEntity
import com.easylife.hobbyreminder.util.coroutine.HobbyReminderDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object RoomCallback: RoomDatabase.Callback(), KoinComponent {

    private val themeDao: ThemeDao by inject()
    private val dispatcher: HobbyReminderDispatcher by inject()

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(dispatcher.io).launch {
            themeDao.insertAllTheme(listOf(
                ThemeEntity(color = R.color.red_a100),
                ThemeEntity(color = R.color.pink_a100),
                ThemeEntity(color = R.color.dark_pink_a100),
                ThemeEntity(color = R.color.purple_a100),
                ThemeEntity(color = R.color.dark_blue_a100),
                ThemeEntity(color = R.color.blue_a100),
                ThemeEntity(color = R.color.light_green_a100),
                ThemeEntity(color = R.color.green_a100),
                ThemeEntity(color = R.color.yellow_a100),
                ThemeEntity(color = R.color.orange_a100),
            ))
        }
    }
}