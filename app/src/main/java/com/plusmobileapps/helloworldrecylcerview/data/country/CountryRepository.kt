package com.plusmobileapps.helloworldrecylcerview.data.country

import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync

class CountryRepository(private val countryDao: CountryDao) : CountryDao {

    override fun getAll(): LiveData<List<Country>> = countryDao.getAll()

    override fun getById(id: Int) = countryDao.getById(id)

    override fun insert(country: Country) {
        GlobalScope.launch {
            countryDao.insert(country)
        }
    }

    override fun delete(country: Country) {
        GlobalScope.launch {
            countryDao.delete(country)
        }
    }

    override fun delete(countryId: Int) {
        GlobalScope.launch {
            countryDao.delete(countryId)
        }
    }
}