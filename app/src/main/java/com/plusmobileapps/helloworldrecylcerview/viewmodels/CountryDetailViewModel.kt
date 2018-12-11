package com.plusmobileapps.helloworldrecylcerview.viewmodels

import androidx.lifecycle.ViewModel
import com.plusmobileapps.helloworldrecylcerview.data.country.CountryRepository

class CountryDetailViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    fun getCountry(id: Int) = countryRepository.getById(id)

}