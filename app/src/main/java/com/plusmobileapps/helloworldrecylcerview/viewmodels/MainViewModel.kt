package com.plusmobileapps.helloworldrecylcerview.viewmodels

import androidx.lifecycle.*
import com.plusmobileapps.helloworldrecylcerview.SingleLiveEvent
import com.plusmobileapps.helloworldrecylcerview.StateReducer
import com.plusmobileapps.helloworldrecylcerview.data.cities.CityRepository
import com.plusmobileapps.helloworldrecylcerview.data.country.CountryRepository
import com.plusmobileapps.helloworldrecylcerview.view.CarouselItem
import com.plusmobileapps.helloworldrecylcerview.view.CarouselType
import com.plusmobileapps.helloworldrecylcerview.view.DataWrapper
import kotlinx.coroutines.*

interface MainView {
    fun onCarouselItemClicked(carouselItem: CarouselItem)
    fun onCardClicked(card: DataWrapper.CardData)
    fun onCardDeleted(card: DataWrapper.CardData)
}

/**
 * simple data holder class for the big cards to be displayed in the recycler view
 */
data class Card(val id: Int, val header: String, val imageUrl: String, val body: String)


/**
 * view model for [com.plusmobileapps.helloworldrecylcerview.view.MainFragment]
 *
 * @property stateReducer helps reduce the state from various data sources into one list to submit to recycler view
 * @property countryRepository source of data for all countries
 * @property openCardLiveEvent an event that will emit the id for the country to open in the [com.plusmobileapps.helloworldrecylcerview.view.CountryDetailFragment]
 * @property openCarouselItemLiveEvent an event that will emit the id for the country to open in the [com.plusmobileapps.helloworldrecylcerview.view.CountryDetailFragment]
 */
class MainViewModel (private val stateReducer: StateReducer,
                     private val countryRepository: CountryRepository,
                     private val cityRepository: CityRepository
) : ViewModel(), MainView {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

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

    private val topCarousel: LiveData<List<CarouselItem>> = Transformations.map(cityRepository.getAll()) { cities ->
        return@map cities.map { city ->
            CarouselItem(city.id!!, city.name, city.description, CarouselType.CITY)
        }
    }

    private val bottomCarousel: LiveData<List<CarouselItem>> = Transformations.map(countryRepository.getAll()) { countries ->
        return@map countries.map { country ->
            CarouselItem(country.id!!, country.name, country.description, CarouselType.COUNTRY)
        }
    }

    /**
     * Wraps the type of data that will be submitted to the recycler view
     * Add all sources of data and emit the change events to the [StateReducer]
     * Add [StateReducer] live data as a source, and have that change event change the actual value inside of the mediator
     */
    private val mediator = MediatorLiveData<List<DataWrapper>>().apply {
        addSource(topCarousel) { stateReducer.onTopCarouselLoaded(it) }
        addSource(bigCards) { stateReducer.onCardsLoaded(it) }
        addSource(bottomCarousel) { stateReducer.onBottomCarouselLoaded(it) }
        addSource(stateReducer.getData()) { t: List<DataWrapper>? -> value = t }
    }

    /**
     * Expose the mediator as a vanilla [LiveData] so that no one from the view can mutate the state
     */
    fun getData(): LiveData<List<DataWrapper>> = mediator
    val openCardLiveEvent = SingleLiveEvent<Int>()
    val openCarouselItemLiveEvent = SingleLiveEvent<Int>()
    val openCityEvent = SingleLiveEvent<Int>()

    override fun onCarouselItemClicked(carouselItem: CarouselItem) {
        when (carouselItem.type) {
            CarouselType.CITY -> openCity(carouselItem.id)
            CarouselType.COUNTRY -> openCountry(carouselItem.id)
        }
    }

    override fun onCardClicked(card: DataWrapper.CardData) {
        openCardLiveEvent.value = card.id
    }

    override fun onCardDeleted(card: DataWrapper.CardData) {
        countryRepository.delete(card.id)
    }

    private fun openCountry(id: Int) = uiScope.launch {
        val country = countryRepository.getById(id)
        withContext(Dispatchers.Main) { openCarouselItemLiveEvent.value = country.id }
    }

    private fun openCity(id: Int) = uiScope.launch {
        val city = cityRepository.getByIdWithCoroutines(id)
        withContext(Dispatchers.Main) { openCityEvent.value = city.id }
    }

}