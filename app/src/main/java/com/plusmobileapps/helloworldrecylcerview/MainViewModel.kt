package com.plusmobileapps.helloworldrecylcerview

import androidx.lifecycle.*
import com.plusmobileapps.helloworldrecylcerview.data.cards.CardsRepository
import com.plusmobileapps.helloworldrecylcerview.data.carousels.CarouselRepository
import com.plusmobileapps.helloworldrecylcerview.view.CarouselItem
import com.plusmobileapps.helloworldrecylcerview.view.DataWrapper

interface View {
    fun onCarouselItemClicked(carouselItem: CarouselItem)
    fun onCardClicked(card: DataWrapper.CardData)
}

class MainViewModel(private val stateReducer: StateReducer = StateReducer(),
                    private val cardsRepository: CardsRepository = CardsRepository(),
                    private val carouselsRepository: CarouselRepository = CarouselRepository()
) : ViewModel(), View {

    private val mediator = MediatorLiveData<DataWrapper>().apply {
        addSource(cardsRepository.getCards()) { stateReducer.onCardsLoaded(it) }
        addSource(carouselsRepository.getCarousels()) { stateReducer.onCarouselItemsLoaded(it) }
    }

    fun start(): LiveData<DataWrapper> = mediator
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