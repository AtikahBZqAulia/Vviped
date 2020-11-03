package com.example.vviped

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_create_campaign.*

class create_campaign : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_campaign)

        backspace.setOnClickListener {
            onBackPressed()
        }

    }
}