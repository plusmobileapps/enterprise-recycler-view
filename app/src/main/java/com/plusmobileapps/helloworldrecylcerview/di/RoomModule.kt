package com.plusmobileapps.helloworldrecylcerview.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.plusmobileapps.helloworldrecylcerview.MyApplication
import com.plusmobileapps.helloworldrecylcerview.data.*
import dagger.Module
import dagger.Provides
import org.jetbrains.anko.doAsync
import javax.inject.Singleton

@Module
class RoomModule(private val application: MyApplication) {

    @Provides
    @Singleton
    fun providesDatabase(): AppDatabase = AppDatabase.getInstance(application)

    @Provides
    @Singleton
    fun providesCountryDao(database: AppDatabase): CountryDao = database.countryDao()

    @Provides
    @Singleton
    fun providesCountryRepository(countryDao: CountryDao): CountryRepository = CountryRepository(countryDao)
}