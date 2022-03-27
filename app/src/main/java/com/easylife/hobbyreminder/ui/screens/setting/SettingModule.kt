package com.easylife.hobbyreminder.ui.screens.setting

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingModule = module {
    viewModel { SettingViewModel() }
    single { SettingScreen() }
}