package com.plusmobileapps.helloworldrecylcerview.di

import com.plusmobileapps.helloworldrecylcerview.view.CountryDetailFragment
import com.plusmobileapps.helloworldrecylcerview.view.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, RoomModule::class, ViewModelModule::class))
interface AppComponent {
    fun inject(mainFragment: MainFragment)
    fun inject(countryDetailFragment: CountryDetailFragment)
}