package com.plusmobileapps.helloworldrecylcerview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.plusmobileapps.helloworldrecylcerview.R
import com.plusmobileapps.helloworldrecylcerview.viewmodels.CountryDetailViewModel
import org.jetbrains.anko.bundleOf
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.IllegalStateException

private const val COUNTRY_ID = "country id"
private const val CITY_ID = "city id"

class CountryDetailFragment : Fragment() {
    private var countryId: Int? = null
    private var cityId: Int? = null
    private lateinit var imageView: ImageView
    private lateinit var header: TextView
    private lateinit var body: TextView

    val viewModel: CountryDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            countryId = it.getInt(COUNTRY_ID)
            cityId = it.getInt(CITY_ID)

            if (countryId == 0) {
                countryId = null
            }

            if (cityId == 0) {
                cityId = null
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_country_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view) {
            imageView = findViewById(R.id.image)
            header = findViewById(R.id.header)
            body = findViewById(R.id.body)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (countryId == null && cityId == null) throw IllegalStateException("No city or country id set for this fragment")

        countryId?.let {
            viewModel.getCountry(it).observe(this, Observer { country ->
                header.text = country.name
                body.text = country.description
                Glide.with(this).load(country.imageUrl).into(imageView)
            })
        }

        cityId?.let {
            viewModel.getCity(it).observe(this, Observer { city ->
                header.text = city.name
                body.text = city.description
                Glide.with(this).load(city.imageUrl).into(imageView)
            })
        }

    }

    companion object {
        @JvmStatic
        fun newInstanceForCountry(countryId: Int) =
            CountryDetailFragment().apply {
                arguments = bundleOf(COUNTRY_ID to countryId)
            }

        @JvmStatic
        fun newInstanceForCity(cityId: Int) =
                CountryDetailFragment().apply {
                    arguments = bundleOf(CITY_ID to cityId)
                }
    }
}
