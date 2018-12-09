package com.plusmobileapps.helloworldrecylcerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.plusmobileapps.helloworldrecylcerview.data.cards.Card
import com.plusmobileapps.helloworldrecylcerview.view.CarouselItem
import com.plusmobileapps.helloworldrecylcerview.view.DataWrapper
import java.util.*

class StateReducer {

    private val masterList = MutableLiveData<List<DataWrapper>>()
    private data class ListState(val cards: List<DataWrapper.CardData>, val carousel: List<DataWrapper.CarouselData>)

    fun getData(): LiveData<List<DataWrapper>> = masterList

    fun onCardsLoaded(cards: List<Card>) {
        val (oldCards, oldCarousel) = splitLists()
        val newCards = cards.map { card ->
            DataWrapper.CardData(card.id, card.header, card.imageUrl, card.body)
        }
        masterList.value = newCards + oldCarousel
    }

    fun onCarouselItemsLoaded(carousels: List<CarouselItem>) {
        val (oldCards, oldCarousel) = splitLists()
        masterList.value = oldCards + DataWrapper.CarouselData(id = UUID.randomUUID().hashCode(), items = carousels)
    }

    private fun splitLists(): ListState {
        val oldList = masterList.value ?: listOf()
        val cards = oldList.filterIsInstance<DataWrapper.CardData>()
        val carousel = oldList.filterIsInstance<DataWrapper.CarouselData>()

        return ListState(
            cards = cards,
            carousel = carousel
        )
    }

}