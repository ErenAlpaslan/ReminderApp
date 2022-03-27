package com.easylife.hobbyreminder

import android.app.Application
import com.easylife.hobbyreminder.common.appModule
import com.easylife.hobbyreminder.ui.screens.home.homeModule
import com.easylife.hobbyreminder.ui.screens.newreminder.newReminderModule
import com.easylife.hobbyreminder.ui.screens.setting.settingModule
import com.easylife.hobbyreminder.ui.screens.splash.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HobbyReminderApplication: Application() {

    private val moduleList = listOf(
        appModule,
        splashModule,
        homeModule,
        newReminderModule,
        settingModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HobbyReminderApplication)
            modules(moduleList)
        }
    }

}