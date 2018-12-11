package com.plusmobileapps.helloworldrecylcerview.data.cities

import androidx.lifecycle.LiveData
import org.jetbrains.anko.doAsync

class CityRepository(private val cityDao: CityDao) : CityDao {

    override fun getAll(): LiveData<List<City>> = cityDao.getAll()

    override fun getById(id: Int): LiveData<City> = cityDao.getById(id)

    override fun insert(city: City) {
        doAsync {
            cityDao.insert(city)
        }
    }

    override fun delete(city: City) {
        doAsync {
            cityDao.delete(city)
        }
    }
}