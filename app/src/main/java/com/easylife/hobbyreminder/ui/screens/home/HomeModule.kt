package com.easylife.hobbyreminder.ui.screens.home

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel() }
    single { HomeScreen() }
}