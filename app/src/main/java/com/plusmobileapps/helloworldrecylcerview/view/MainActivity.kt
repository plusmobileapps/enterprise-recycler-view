package com.plusmobileapps.helloworldrecylcerview.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
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
        supportFragmentManager.transaction {
            replace(R.id.fragment_container, MainFragment())
        }
    }

}




