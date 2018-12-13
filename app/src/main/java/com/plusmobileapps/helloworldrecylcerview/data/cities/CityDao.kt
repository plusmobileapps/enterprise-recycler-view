package com.plusmobileapps.helloworldrecylcerview.data.cities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CityDao {

    @Query("SELECT * FROM city")
    fun getAll(): LiveData<List<City>>

    @Query("SELECT * FROM city WHERE id IN (:id)")
    fun getById(id: Int): LiveData<City>

    @Query("SELECT * FROM city WHERE id IN (:id)")
    fun getByIdNotLive(id: Int): City

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: City)

    @Delete
    fun delete(city: City)

}