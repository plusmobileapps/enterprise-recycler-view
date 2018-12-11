package com.plusmobileapps.helloworldrecylcerview.view

import androidx.lifecycle.ViewModel
import com.plusmobileapps.helloworldrecylcerview.data.CountryRepository

class CountryDetailViewModel (private val countryRepository: CountryRepository): ViewModel() {

    fun getCountry(id: Int) = countryRepository.getById(id)
}