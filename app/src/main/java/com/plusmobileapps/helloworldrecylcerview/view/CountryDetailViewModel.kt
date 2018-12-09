package com.plusmobileapps.helloworldrecylcerview.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.plusmobileapps.helloworldrecylcerview.Card
import com.plusmobileapps.helloworldrecylcerview.data.CountryRepository
import javax.inject.Inject

class CountryDetailViewModel @Inject constructor(private val countryRepository: CountryRepository): ViewModel() {

    fun getCountry(id: Int) = countryRepository.getById(id)
}