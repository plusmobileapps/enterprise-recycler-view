package com.plusmobileapps.helloworldrecylcerview.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.plusmobileapps.helloworldrecylcerview.R
import com.plusmobileapps.helloworldrecylcerview.data.cards.CardsRepository
import java.lang.IllegalStateException

private const val COUNTRY_ID = "country id"

class CountryDetailFragment : Fragment() {
    private var id: Int? = null
    private lateinit var imageView: ImageView
    private lateinit var header: TextView
    private lateinit var body: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(COUNTRY_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view) {
            imageView = findViewById(R.id.image)
            header = findViewById(R.id.header)
            body = findViewById(R.id.body)
        }

        val cardId = id ?: throw IllegalStateException("No id was set on this fragment")

        val card = CardsRepository.getCard(cardId) ?: throw IllegalStateException("Requested Id was not in the repository")

        Glide.with(this).load(card.imageUrl).into(imageView)
        header.text = card.header
        body.text = card.body
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CountryDetailFragment.
         */
        @JvmStatic
        fun newInstance(param1: Int) =
            CountryDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(COUNTRY_ID, param1)
                }
            }
    }
}
