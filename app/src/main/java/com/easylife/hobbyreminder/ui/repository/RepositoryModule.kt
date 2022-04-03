package com.easylife.hobbyreminder.ui.repository

import org.koin.dsl.module

val repositoryModule = module {
    single { ThemeRepository(get(), get()) }
}