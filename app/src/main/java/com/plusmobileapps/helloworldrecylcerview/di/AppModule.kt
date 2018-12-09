package com.plusmobileapps.helloworldrecylcerview.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.plusmobileapps.helloworldrecylcerview.MainViewModel
import com.plusmobileapps.helloworldrecylcerview.MyApplication
import com.plusmobileapps.helloworldrecylcerview.StateReducer
import com.plusmobileapps.helloworldrecylcerview.data.AppDatabase
import com.plusmobileapps.helloworldrecylcerview.data.CountryDao
import com.plusmobileapps.helloworldrecylcerview.data.CountryRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule (private val application: MyApplication) {

    @Provides
    @Singleton
    fun providesMyApplication(): MyApplication = application

    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application.applicationContext

    @Provides
    fun providesStateReducer() = StateReducer()


}