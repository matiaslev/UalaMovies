package com.matiaslev.ualamovies.presentation

import android.app.Application
import com.matiaslev.ualamovies.di.ualaModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UalaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@UalaApplication)
            modules(ualaModule)
        }
    }
}