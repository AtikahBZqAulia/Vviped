package com.example.vviped.twitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.vviped.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class twitterMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavView)
        val navController = findNavController(R.id.twitterfragment)

        bottomNavigationView.setupWithNavController(navController)
    }
}