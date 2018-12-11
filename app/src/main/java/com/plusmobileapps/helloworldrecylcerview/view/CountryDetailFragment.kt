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
import org.koin.android.viewmodel.ext.android.viewModel

private const val COUNTRY_ID = "country id"

class CountryDetailFragment : Fragment() {
    private var id: Int? = null
    private lateinit var imageView: ImageView
    private lateinit var header: TextView
    private lateinit var body: TextView

    val viewModel: CountryDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(COUNTRY_ID)
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
        val countryId = id ?: throw IllegalStateException("No id was set on this fragment")
        val card = viewModel.getCountry(countryId)
        card.observe(this, Observer { country ->
            Glide.with(this).load(country.imageUrl).into(imageView)
            header.text = country.name
            body.text = country.description
        })

    }

    companion object {
        @JvmStatic
        fun newInstance(countryId: Int) =
            CountryDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(COUNTRY_ID, countryId)
                }
            }
    }
}
