package com.plusmobileapps.helloworldrecylcerview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.plusmobileapps.helloworldrecylcerview.data.cities.City
import com.plusmobileapps.helloworldrecylcerview.data.cities.CityRepository
import com.plusmobileapps.helloworldrecylcerview.data.country.Country
import com.plusmobileapps.helloworldrecylcerview.data.country.CountryRepository

class CountryDetailViewModel(private val countryRepository: CountryRepository,
                             private val cityRepository: CityRepository) : ViewModel() {

    fun getCountry(id: Int): LiveData<Country> = countryRepository.getById(id)

    fun getCity(id: Int): LiveData<City> = cityRepository.getById(id)

}