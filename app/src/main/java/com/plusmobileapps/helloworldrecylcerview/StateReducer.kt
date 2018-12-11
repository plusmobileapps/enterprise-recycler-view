package com.plusmobileapps.helloworldrecylcerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.plusmobileapps.helloworldrecylcerview.view.CarouselItem
import com.plusmobileapps.helloworldrecylcerview.view.DataWrapper
import com.plusmobileapps.helloworldrecylcerview.viewmodels.Card

private const val TOP_CAROUSEL_ID = -42
private const val BOTTOM_CAROUSEL_ID = -34

class StateReducer {

    private val masterList = MutableLiveData<List<DataWrapper>>()

    private data class ListState(val cards: MutableList<DataWrapper.CardData>?, val topCarousel: DataWrapper.CarouselData?, val bottomCarousel: DataWrapper.CarouselData?)

    fun getData(): LiveData<List<DataWrapper>> = masterList

    fun onCardsLoaded(cards: List<Card>) {
        val (oldCards, topCarousel, bottomCarousel) = splitLists()

        val newList = mutableListOf<DataWrapper>()
        val newCards = cards.map { card ->
            DataWrapper.CardData(card.id, card.header, card.imageUrl, card.body)
        }
        topCarousel?.let { newList.add(it) }
        newList.addAll(newCards)
        bottomCarousel?.let { newList.add(it) }
        masterList.value = newList
    }

    fun onTopCarouselLoaded(carousels: List<CarouselItem>) {
        val (cards, oldTopCarousel, bottomCarousel) = splitLists()
        val newList = mutableListOf<DataWrapper>().apply {
            add(DataWrapper.CarouselData(TOP_CAROUSEL_ID, carousels))
            cards?.let { addAll(it) }
            bottomCarousel?.let { add(it) }
        }

        masterList.value = newList
    }

    fun onBottomCarouselLoaded(carousels: List<CarouselItem>) {
        val (cards, topCarousel, oldBottomCarousel) = splitLists()
        val newList = mutableListOf<DataWrapper>().apply {
            topCarousel?.let { add(it) }
            cards?.let { addAll(it) }
            add(DataWrapper.CarouselData(BOTTOM_CAROUSEL_ID, carousels))
        }

        masterList.value = newList
    }

    private fun splitLists(): ListState {
        val oldList = masterList.value ?: listOf()
        val cards = oldList.filterIsInstance<DataWrapper.CardData>()
        val topCarousel = oldList.find { it.id ==  TOP_CAROUSEL_ID && it is DataWrapper.CarouselData }
        val bottomCarousel = oldList.findLast { it.id == BOTTOM_CAROUSEL_ID && it is DataWrapper.CarouselData }

        return ListState(
            cards = cards.toMutableList(),
            topCarousel = topCarousel as? DataWrapper.CarouselData,
            bottomCarousel = bottomCarousel as? DataWrapper.CarouselData
        )
    }

}