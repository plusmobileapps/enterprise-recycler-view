package com.plusmobileapps.helloworldrecylcerview.viewmodels

import androidx.lifecycle.*
import com.plusmobileapps.helloworldrecylcerview.SingleLiveEvent
import com.plusmobileapps.helloworldrecylcerview.StateReducer
import com.plusmobileapps.helloworldrecylcerview.data.*
import com.plusmobileapps.helloworldrecylcerview.view.CarouselItem
import com.plusmobileapps.helloworldrecylcerview.view.DataWrapper

interface View {
    fun onCarouselItemClicked(carouselItem: CarouselItem)
    fun onCardClicked(card: DataWrapper.CardData)
    fun onCardDeleted(card: DataWrapper.CardData)
}

data class Card(val id: Int, val header: String, val imageUrl: String, val body: String)

class MainViewModel (private val stateReducer: StateReducer,
                     private val countryRepository: CountryRepository
) : ViewModel(), View {

    private val bigCards: LiveData<List<Card>> = Transformations.map(countryRepository.getAll()) { countries ->
        return@map countries.map { country ->
            Card(
                country.id!!,
                country.name,
                country.imageUrl,
                country.description
            )
        }
    }

    private val topCarousel: LiveData<List<CarouselItem>> = Transformations.map(countryRepository.getAll()) { countries ->
        return@map countries.map { country ->
            CarouselItem(country.id!!, country.name, country.description)
        }
    }

    private val bottomCarousel: LiveData<List<CarouselItem>> = Transformations.map(countryRepository.getAll()) { countries ->
        return@map countries.map { country ->
            CarouselItem(country.id!!, country.name, country.description)
        }
    }

    private val mediator = MediatorLiveData<List<DataWrapper>>().apply {
        addSource(topCarousel) { stateReducer.onTopCarouselLoaded(it) }
        addSource(bigCards) { stateReducer.onCardsLoaded(it) }
        addSource(bottomCarousel) { stateReducer.onBottomCarouselLoaded(it) }
        addSource(stateReducer.getData()) { t: List<DataWrapper>? -> value = t }
    }

    fun getData(): LiveData<List<DataWrapper>> = mediator
    val cardClicked = SingleLiveEvent<Int>()
    val carouselItemClicked = SingleLiveEvent<Int>()

    override fun onCarouselItemClicked(carouselItem: CarouselItem) {
        carouselItemClicked.value = carouselItem.id
    }

    override fun onCardClicked(card: DataWrapper.CardData) {
        cardClicked.value = card.id
    }

    override fun onCardDeleted(card: DataWrapper.CardData) {
        countryRepository.delete(card.id)
    }
}