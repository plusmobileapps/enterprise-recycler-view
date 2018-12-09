package com.plusmobileapps.helloworldrecylcerview.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.plusmobileapps.helloworldrecylcerview.MainViewModel
import com.plusmobileapps.helloworldrecylcerview.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val adapter = RecyclerViewListAdapter(
            carouselItemClickListener = { viewModel.onCarouselItemClicked(it) },
            cardClickListener = { viewModel.onCardClicked(it) },
            glide = Glide.with(this)
        )

        findViewById<RecyclerView>(R.id.recyclerview).apply {
            this.adapter = adapter
        }

        viewModel.start().observe(this, Observer {  })

        viewModel.getData().observe(this, Observer { list ->
            adapter.submitList(list)
        })
    }

}




