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
import com.plusmobileapps.helloworldrecylcerview.MyApplication
import com.plusmobileapps.helloworldrecylcerview.R
import com.plusmobileapps.helloworldrecylcerview.di.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MyApplication.appComponent.inject(this)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
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