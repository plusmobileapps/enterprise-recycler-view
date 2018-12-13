package com.plusmobileapps.helloworldrecylcerview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.plusmobileapps.helloworldrecylcerview.viewmodels.MainViewModel
import com.plusmobileapps.helloworldrecylcerview.R
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    val viewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = RecyclerViewListAdapter(
            carouselItemClickListener = { viewModel.onCarouselItemClicked(it) },
            cardClickListener = { viewModel.onCardClicked(it) },
            cardDeleteListener = { viewModel.onCardDeleted(it) },
            glide = Glide.with(this)
        )

        view?.findViewById<RecyclerView>(R.id.recyclerview).apply {
            this?.adapter = adapter
        }

        with(viewModel) {
            getData().observe(this@MainFragment, Observer { data ->
                adapter.submitList(data)
            })

            openCardLiveEvent.observe(this@MainFragment, Observer { id ->
                openCard(id)
            })

            openCarouselItemLiveEvent.observe(this@MainFragment, Observer { id ->
                openCarouselItem(id)
            })

            openCityEvent.observe(this@MainFragment, Observer { id ->
                openCity(id)
            })
        }
    }

    private fun openCard(id: Int) {
        val fragment = CountryDetailFragment.newInstanceForCountry(id)
        fragmentManager?.transaction {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }

    private fun openCity(id: Int) {
        val fragment = CountryDetailFragment.newInstanceForCity(id)
        fragmentManager?.transaction {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }

    private fun openCarouselItem(id: Int) {
        val fragment = CountryDetailFragment.newInstanceForCountry(id)
        fragmentManager?.transaction {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }
}