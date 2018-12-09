package com.plusmobileapps.helloworldrecylcerview.data.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class Card(val id: Int, val header: String, val imageUrl: String, val body: String)

class CardsRepository {

    private val cards = MutableLiveData<List<Card>>().apply {
        value = listOf(
            Card(
                id = 1,
                header = "Shanghai",
                imageUrl = "https://inassets1-internationsgmbh.netdna-ssl.com/image/static_1280_720/static/bundles/internationsseo/frontend/images/localcommunityHero/189.jpg",
                body = "Shanghai is one of the four municipalities under the direct administration of the central government of the People's Republic of China, the largest city in China"
            ),
            Card(
                id = 2,
                header = "Russia",
                imageUrl = "https://thehill.com/sites/default/files/styles/thumb_small_article/public/article_images/russia-kremlin-getty.jpg?itok=1UAW9Rpn",
                body = "Russia officially the Russian Federation is a country in Eurasia. At 17,125,200 square kilometres (6,612,100 sq mi), Russia is the largest country in the world by ..."
            ),
            Card(
                id = 3,
                header = "USA",
                imageUrl = "http://cdn.playbuzz.com/cdn/17554002-7f71-4a44-835e-d85eb5abbb86/bb3f7991-bb54-4053-89c3-4e436e8b9a52.jpg",
                body = "The United States of America (USA), commonly known as the United States (U.S. or US) or America, is a country composed of 50 states, a federal district, five ..."
            )
        )
    }

    fun getCards(): LiveData<List<Card>> = cards

}