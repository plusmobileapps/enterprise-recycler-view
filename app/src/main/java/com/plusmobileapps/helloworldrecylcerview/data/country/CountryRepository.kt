package com.plusmobileapps.helloworldrecylcerview.data.country

import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync

class CountryRepository(private val countryDao: CountryDao) {

    fun getAll(): LiveData<List<Country>> = countryDao.getAll()

    fun getById(id: Int): Country = countryDao.getById(id)

    fun getByIdLive(id: Int): LiveData<Country> = countryDao.getByIdLive(id)

    fun insert(country: Country) {
        GlobalScope.launch {
            countryDao.insert(country)
        }
    }

    fun delete(country: Country) {
        GlobalScope.launch {
            countryDao.delete(country)
        }
    }

    fun delete(countryId: Int) {
        GlobalScope.launch {
            countryDao.delete(countryId)
        }
    }
}