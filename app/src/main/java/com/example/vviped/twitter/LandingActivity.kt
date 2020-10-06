package com.example.vviped.twitter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.vviped.LoginActivity

import com.example.vviped.R
import com.example.vviped.activity_landing
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_twitter_landing.*

class LandingActivity : AppCompatActivity() {

    lateinit var handler: Handler;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_landing)

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)
            finish()
        }, 3000) //delay 3sec and open landing activity

    }
}
