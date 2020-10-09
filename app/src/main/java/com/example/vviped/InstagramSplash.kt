package com.example.vviped

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.vviped.twitter.twitter_login

class InstagramSplash : AppCompatActivity() {

    lateinit var handler: Handler;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instagram_splash)

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)
            finish()
        }, 2000) //delay 3sec and open landing activity


    }
}