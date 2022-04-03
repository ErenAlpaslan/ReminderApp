package com.easylife.hobbyreminder.ui.repository

import com.easylife.hobbyreminder.common.persistence.ReminderResult
import com.easylife.hobbyreminder.common.persistence.dao.ThemeDao
import com.easylife.hobbyreminder.entity.ThemeEntity
import com.easylife.hobbyreminder.util.coroutine.HobbyReminderDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class ThemeRepository(
    private val themeDao: ThemeDao,
    private val dispatcher: HobbyReminderDispatcher
) {

    suspend fun getThemes() = flow<ReminderResult<List<ThemeEntity>>> {
        try {
            emit(ReminderResult.Success(data = themeDao.getAllThemes()))
        }catch (e: Exception) {
            emit(ReminderResult.Error(message = "Couldn't get themes! an error occurred"))
        }
    }.flowOn(dispatcher.io)

}