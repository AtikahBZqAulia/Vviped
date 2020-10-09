package com.example.vviped.twitter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import com.example.vviped.R

class LandingActivity : AppCompatActivity() {

    lateinit var handler: Handler;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_splash)

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, twitter_login::class.java )
            startActivity(intent)
            finish()
        }, 2000) //delay 3sec and open landing activity

    }
}
