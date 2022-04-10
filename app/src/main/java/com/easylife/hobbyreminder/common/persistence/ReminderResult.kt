package com.easylife.hobbyreminder.common.persistence

sealed class ReminderResult <T> {
    data class Success <T> (val data: T?): ReminderResult<T>()
    data class Error <T> (val message: String?) : ReminderResult<T>()
}
