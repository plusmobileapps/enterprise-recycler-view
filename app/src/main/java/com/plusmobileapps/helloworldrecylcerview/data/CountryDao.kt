package com.plusmobileapps.helloworldrecylcerview.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CountryDao {

    @Query("SELECT * FROM country")
    fun getAll(): LiveData<List<Country>>

    @Query("SELECT * FROM country WHERE id IN (:id)")
    fun getById(id: Int): LiveData<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: Country)

    @Delete
    fun delete(country: Country)

}