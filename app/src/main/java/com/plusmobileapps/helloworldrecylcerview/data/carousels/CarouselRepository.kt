package com.plusmobileapps.helloworldrecylcerview.data.carousels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.plusmobileapps.helloworldrecylcerview.view.CarouselItem

object CarouselRepository {

    private val carousels = MutableLiveData<List<CarouselItem>>().apply {
        value = listOf(
            CarouselItem(
                id = 1,
                header = "Shanghai",
                body = "Shanghai is one of the four municipalities under the direct administration of the central government of the People's Republic of China, the largest city in China"
            ), CarouselItem(
                id = 2,
                header = "Russia",
                body = "Russia officially the Russian Federation is a country in Eurasia. At 17,125,200 square kilometres (6,612,100 sq mi), Russia is the largest country in the world by ..."
            ), CarouselItem(
                id = 3,
                header = "USA",
                body = "The United States of America (USA), commonly known as the United States (U.S. or US) or America, is a country composed of 50 states, a federal district, five ..."
            ), CarouselItem(
                id = 4,
                header = "Shanghai",
                body = "Shanghai is one of the four municipalities under the direct administration of the central government of the People's Republic of China, the largest city in China"
            ), CarouselItem(
                id = 5,
                header = "Russia",
                body = "Russia officially the Russian Federation is a country in Eurasia. At 17,125,200 square kilometres (6,612,100 sq mi), Russia is the largest country in the world by ..."
            ), CarouselItem(
                id = 6,
                header = "USA",
                body = "The United States of America (USA), commonly known as the United States (U.S. or US) or America, is a country composed of 50 states, a federal district, five ..."
            )
        )
    }

    fun getCarousels(): LiveData<List<CarouselItem>> = carousels

}