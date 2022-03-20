package com.easylife.hobbyreminder.util.coroutine

import kotlinx.coroutines.CoroutineDispatcher

abstract class IHobbyReminderDispatchers {

    abstract val main: CoroutineDispatcher

    abstract val io: CoroutineDispatcher

    abstract val default: CoroutineDispatcher
}