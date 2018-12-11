package com.plusmobileapps.helloworldrecylcerview.di

import com.plusmobileapps.helloworldrecylcerview.viewmodels.MainViewModel
import com.plusmobileapps.helloworldrecylcerview.StateReducer
import com.plusmobileapps.helloworldrecylcerview.data.AppDatabase
import com.plusmobileapps.helloworldrecylcerview.data.CountryDao
import com.plusmobileapps.helloworldrecylcerview.data.CountryRepository
import com.plusmobileapps.helloworldrecylcerview.viewmodels.CountryDetailViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    single { AppDatabase.getInstance(androidApplication()) }
    single { get<AppDatabase>().countryDao() }
    single { CountryRepository(get()) }
    factory { StateReducer() }
    viewModel { MainViewModel(get(), get()) }
    viewModel { CountryDetailViewModel(get()) }

}