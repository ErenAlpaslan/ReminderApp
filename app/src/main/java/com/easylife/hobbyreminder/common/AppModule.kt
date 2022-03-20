package com.easylife.hobbyreminder.common

import com.easylife.hobbyreminder.util.coroutine.HobbyReminderDispatcher
import org.koin.dsl.module

val appModule = module {
    single { HobbyReminderDispatcher() }
}