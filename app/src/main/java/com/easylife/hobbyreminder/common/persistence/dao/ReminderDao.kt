package com.easylife.hobbyreminder.common.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.easylife.hobbyreminder.entity.ReminderConfig

@Dao
interface ReminderDao {

   @Insert
   suspend fun insertNewReminder(reminderConfig: ReminderConfig)

   @Query("SELECT * FROM Reminders")
   suspend fun getReminders(): List<ReminderConfig>

   @Query("SELECT * FROM Reminders WHERE id == :id")
   suspend fun getReminder(id: Int): ReminderConfig
}