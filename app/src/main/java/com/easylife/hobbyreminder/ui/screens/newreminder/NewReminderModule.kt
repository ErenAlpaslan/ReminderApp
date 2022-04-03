package com.easylife.hobbyreminder.ui.screens.newreminder

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newReminderModule = module {
    viewModel { NewReminderViewModel(get()) }
    single { NewReminderScreen() }
}