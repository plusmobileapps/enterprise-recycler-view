package com.plusmobileapps.helloworldrecylcerview

import android.app.Application
import com.plusmobileapps.helloworldrecylcerview.di.*
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //initialize dependency injection
        startKoin(this, listOf(appModule))
    }
}