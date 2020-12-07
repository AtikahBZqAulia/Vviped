package com.mobcomp.vviped

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mobcomp.vviped.model.login.PreferenceHelper

class splashScreen : AppCompatActivity() {
    lateinit var handler: Handler;
    lateinit var sharedPref: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sharedPref = PreferenceHelper(this)


        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this,IntroSlider::class.java )
            startActivity(intent)
            finish()
        }, 2000) //delay 3sec and open landing activity


    }
}