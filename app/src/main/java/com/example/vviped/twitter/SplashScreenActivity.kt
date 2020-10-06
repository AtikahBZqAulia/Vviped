package com.example.vviped.twitter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import com.example.vviped.R
import com.example.vviped.activity_landing
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    lateinit var handler: Handler;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, activity_landing::class.java )
            startActivity(intent)
            finish()
        }, 2500) //delay 3sec and open landing activity



    }
}
