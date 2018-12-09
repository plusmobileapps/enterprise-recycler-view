package com.plusmobileapps.helloworldrecylcerview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.plusmobileapps.helloworldrecylcerview.MainViewModel
import com.plusmobileapps.helloworldrecylcerview.R

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val adapter = RecyclerViewListAdapter(
            carouselItemClickListener = { viewModel.onCarouselItemClicked(it) },
            cardClickListener = { viewModel.onCardClicked(it) },
            glide = Glide.with(this)
        )

        view.findViewById<RecyclerView>(R.id.recyclerview).apply {
            this.adapter = adapter
        }

        with(viewModel) {
            start().observe(this@MainFragment, Observer {  })

            getData().observe(this@MainFragment, Observer { list ->
                adapter.submitList(list)
            })

            cardClicked.observe(this@MainFragment, Observer { id ->
                openCard(id)
            })

            carouselItemClicked.observe(this@MainFragment, Observer {  id ->
                openCarouselItem(id)
            })
        }

    }

    private fun openCard(id: Int) {
        val fragment = CountryDetailFragment.newInstance(id)
        fragmentManager?.transaction {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }

    private fun openCarouselItem(id: Int) {
        val fragment = CountryDetailFragment.newInstance(id)
        fragmentManager?.transaction {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }
}