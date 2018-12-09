package com.plusmobileapps.helloworldrecylcerview

import android.app.Application
import com.plusmobileapps.helloworldrecylcerview.di.AppComponent
import com.plusmobileapps.helloworldrecylcerview.di.AppModule
import com.plusmobileapps.helloworldrecylcerview.di.DaggerAppComponent
import com.plusmobileapps.helloworldrecylcerview.di.RoomModule

class MyApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule(this))
            .build()
    }
}