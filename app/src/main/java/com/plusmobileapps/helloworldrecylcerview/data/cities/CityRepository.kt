package com.plusmobileapps.helloworldrecylcerview.data.cities

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync

class CityRepository(private val cityDao: CityDao) {

    fun getAll(): LiveData<List<City>> = cityDao.getAll()

    fun getById(id: Int): LiveData<City> = cityDao.getById(id)

    suspend fun getByIdWithCoroutines(id: Int) = cityDao.getByIdNotLive(id)

    fun insert(city: City) {
        GlobalScope.launch {
            cityDao.insert(city)
        }
    }

    fun delete(city: City) {
        GlobalScope.launch {
            cityDao.delete(city)
        }
    }
}