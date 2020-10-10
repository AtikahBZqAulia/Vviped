package com.example.vviped.twitter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vviped.MainActivity
import com.example.vviped.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.login_button
import kotlinx.android.synthetic.main.activity_twitter_login.*

class twitter_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_login)

        btn_login_twitter.setOnClickListener{
            startActivity(Intent(this, twitterMain::class.java))
        }
    }

}