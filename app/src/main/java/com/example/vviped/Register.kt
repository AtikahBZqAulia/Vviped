package com.example.vviped

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        imageBackspace.setOnClickListener{
            onBackPressed()
        }

       textLoginHere.setOnClickListener{
            onBackPressed()
        }
    }

}