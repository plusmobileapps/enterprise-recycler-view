package com.plusmobileapps.helloworldrecylcerview.data.cities

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync

class CityRepository(private val cityDao: CityDao) : CityDao {

    override fun getAll(): LiveData<List<City>> = cityDao.getAll()

    override fun getById(id: Int): LiveData<City> = cityDao.getById(id)

    override fun insert(city: City) {
        GlobalScope.launch {
            cityDao.insert(city)
        }
    }

    override fun delete(city: City) {
        GlobalScope.launch {
            cityDao.delete(city)
        }
    }
}