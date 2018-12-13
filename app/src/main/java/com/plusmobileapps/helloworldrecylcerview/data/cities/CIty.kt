package com.plusmobileapps.helloworldrecylcerview.data.cities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo
    val description: String,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String

)
