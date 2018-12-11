package com.plusmobileapps.helloworldrecylcerview.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.plusmobileapps.helloworldrecylcerview.data.cities.City
import com.plusmobileapps.helloworldrecylcerview.data.cities.CityDao
import com.plusmobileapps.helloworldrecylcerview.data.country.Country
import com.plusmobileapps.helloworldrecylcerview.data.country.CountryDao
import org.jetbrains.anko.doAsync

@Database(entities = arrayOf(Country::class, City::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized<AppDatabase>(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }


        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "appDb.db"
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    doAsync {
                        val roomDatabase = getInstance(context)
                        insertCities(roomDatabase.cityDao())
                        insertCountries(roomDatabase.countryDao())
                    }
                }
            })
                .build()
        }
    }

}

fun insertCities(cityDao: CityDao) {
    with(cityDao) {
        insert(
            City(
                name = "Hamburg",
                country = "Germany",
                description = "Hamburg is the second-largest city in Germany with a population of over 1.8 million. One of Germany's 16 federal states, it is surrounded by Schleswig-Holstein ..."
            )
        )
        insert(
            City(
                name = "Seattle",
                country = "USA",
                description = "Seattle is a seaport city on the West Coast of the United States. It is the seat of King County, Washington. With an estimated 730,000 residents as of 2018, Seattle ..."
            )
        )
        insert(
            City(
                name = "London",
                country = "UK",
                description = "London is the capital and largest city of both the United Kingdom and England. Standing on the River Thames in southeastern England, 50 miles (80 km) ..."
            )
        )
        insert(
            City(
                name = "Paris",
                country = "France",
                description = "Paris is the capital and most populous city of France, with an area of 105 square kilometres (41 square miles) and a population of 2,206,488. Since the 17th ..."
            )
        )
    }
}

fun insertCountries(countryDao: CountryDao) {
    with(countryDao) {
        insert(
            Country(
                name = "China",
                imageUrl = "https://inassets1-internationsgmbh.netdna-ssl.com/image/static_1280_720/static/bundles/internationsseo/frontend/images/localcommunityHero/189.jpg",
                description = "China, officially the People's Republic of China (PRC) since 1949, is a country in East Asia and the world's most populous country, with a population of around ..."
            )
        )
        insert(
            Country(
                name = "Russia",
                imageUrl = "https://thehill.com/sites/default/files/styles/thumb_small_article/public/article_images/russia-kremlin-getty.jpg?itok=1UAW9Rpn",
                description = "Russia officially the Russian Federation is a country in Eurasia. At 17,125,200 square kilometres (6,612,100 sq mi), Russia is the largest country in the world by ..."
            )
        )
        insert(
            Country(
                name = "USA",
                imageUrl = "http://cdn.playbuzz.com/cdn/17554002-7f71-4a44-835e-d85eb5abbb86/bb3f7991-bb54-4053-89c3-4e436e8b9a52.jpg",
                description = "The United States of America (USA), commonly known as the United States (U.S. or US) or America, is a country composed of 50 states, a federal district, five ..."

            )
        )
        insert(
            Country(
                name = "Germany",
                imageUrl = "https://www.studying-in-germany.org/wp-content/uploads/2018/07/German-Culture-and-Traditions-696x464.jpg",
                description = "Location of Germany (dark green). – in Europe (green & dark grey) – in the European Union (green). Location of Germany. Capital. and largest city. Berlin"
            )
        )
        insert(
            Country(
                name = "England",
                imageUrl = "https://dynaimage.cdn.cnn.com/cnn/q_auto,w_900,c_fill,g_auto,h_506,ar_16:9/http%3A%2F%2Fcdn.cnn.com%2Fcnnnext%2Fdam%2Fassets%2F160418173456-beautiful-england-18-london-england.jpg",
                description = "Location of England (dark green). – in Europe (green & dark grey) – in the United Kingdom (green). Status, Country. Capital. and largest city. London · 51°30′N ..."
            )
        )
        insert(
            Country(
                name = "Canada",
                imageUrl = "https://www.ottawatourism.ca/wp-content/uploads/2018/06/Canada-Day-Parliament-Hill-dusk-044A2535-photographer-Taylor-Burk-Photography.jpg",
                description = "Canada is a country located in the northern part of North America. Its ten provinces and three territories extend from the Atlantic to the Pacific and northward into ..."
            )
        )
        insert(
            Country(
                name = "Mexico",
                imageUrl = "https://i.dmarge.com/2017/06/mexico-city-1-960x580.jpg",
                description = "Mexico officially the United Mexican States is a country in the southern portion of North America. It is bordered to the north by the United States; to the south and ..."
            )
        )
        insert(
            Country(
                name = "Japan",
                imageUrl = "https://cdn.tourradar.com/s3/tour/original/96639_55939865.jpg",
                description = "Japan is an island country in East Asia. Located in the Pacific Ocean, it lies off the eastern coast of the Asian continent and stretches from the Sea of Okhotsk in ..."
            )
        )
    }
}

