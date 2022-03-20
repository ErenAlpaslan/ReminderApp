package com.easylife.hobbyreminder

import android.app.Application
import com.easylife.hobbyreminder.common.appModule
import com.easylife.hobbyreminder.ui.screens.newreminder.newReminderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HobbyReminderApplication: Application() {

    private val moduleList = listOf(
        appModule,
        newReminderModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HobbyReminderApplication)
            modules(moduleList)
        }
    }

}