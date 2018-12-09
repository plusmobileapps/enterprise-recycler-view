package com.plusmobileapps.helloworldrecylcerview.data

import androidx.lifecycle.LiveData
import org.jetbrains.anko.doAsync

class CountryRepository(private val countryDao: CountryDao) : CountryDao {

    override fun getAll(): LiveData<List<Country>> = countryDao.getAll()

    override fun getById(id: Int) = countryDao.getById(id)

    override fun insert(country: Country) {
        doAsync {
            countryDao.insert(country)
        }
    }

    override fun delete(country: Country) {
        doAsync {
            countryDao.delete(country)
        }
    }

}