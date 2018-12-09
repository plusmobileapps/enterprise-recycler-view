package com.plusmobileapps.helloworldrecylcerview

import androidx.lifecycle.*
import com.plusmobileapps.helloworldrecylcerview.data.*
import com.plusmobileapps.helloworldrecylcerview.view.CarouselItem
import com.plusmobileapps.helloworldrecylcerview.view.DataWrapper
import javax.inject.Inject

interface View {
    fun onCarouselItemClicked(carouselItem: CarouselItem)
    fun onCardClicked(card: DataWrapper.CardData)
}

data class Card(val id: Int, val header: String, val imageUrl: String, val body: String)

class MainViewModel @Inject constructor(private val stateReducer: StateReducer,
                    private val countryRepository: CountryRepository
) : ViewModel(), View {

    private val bigCards: LiveData<List<Card>> = Transformations.map(countryRepository.getAll()) { countries ->
        return@map countries.map { country ->
            Card(country.id!!, country.name, country.imageUrl, country.description)
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

    private val mediator = MediatorLiveData<DataWrapper>().apply {
        addSource(topCarousel) { stateReducer.onTopCarouselLoaded(it) }
        addSource(bigCards) { stateReducer.onCardsLoaded(it) }
        addSource(bottomCarousel) { stateReducer.onBottomCarouselLoaded(it) }
    }

    fun start(): LiveData<Unit> = Transformations.map(mediator, { return@map Unit })
    fun getData(): LiveData<List<DataWrapper>> = stateReducer.getData()
    val cardClicked = SingleLiveEvent<Int>()
    val carouselItemClicked = SingleLiveEvent<Int>()

    override fun onCarouselItemClicked(carouselItem: CarouselItem) {
        carouselItemClicked.value = carouselItem.id
    }

    override fun onCardClicked(card: DataWrapper.CardData) {
        cardClicked.value = card.id
    }
}