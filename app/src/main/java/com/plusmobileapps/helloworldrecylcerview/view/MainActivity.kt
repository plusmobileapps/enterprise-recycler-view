package com.plusmobileapps.helloworldrecylcerview.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
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




