package com.example.vviped

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vviped.twitter.LandingActivity
import kotlinx.android.synthetic.main.activity_landing.*

class activity_landing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        sign_ig_link.setOnClickListener{
        startActivity(Intent(this, InstagramSplash::class.java))
    }
        btn_login_twitter.setOnClickListener{
            startActivity(Intent(this, LandingActivity::class.java))
        }
}
}