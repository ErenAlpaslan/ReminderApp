package com.easylife.hobbyreminder.ui.screens.newreminder

import com.easylife.hobbyreminder.common.persistence.ReminderResult
import com.easylife.hobbyreminder.common.persistence.dao.ReminderDao
import com.easylife.hobbyreminder.entity.ReminderConfig
import com.easylife.hobbyreminder.util.coroutine.HobbyReminderDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

class ReminderRepository(
    private val reminderDao: ReminderDao,
    private val hobbyReminderDispatcher: HobbyReminderDispatcher
) {

    suspend fun insertNewReminder(reminderConfig: ReminderConfig) = flow {
        try {
            reminderDao.insertNewReminder(reminderConfig)
            emit(ReminderResult.Success(true))
        }catch (e: Exception) {
            emit(ReminderResult.Error(e.message))
        }
    }.flowOn(hobbyReminderDispatcher.io)


}