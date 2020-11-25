package com.example.vviped.model.login

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vviped.Landing
import com.example.vviped.MainActivity
import com.example.vviped.R
import kotlinx.android.synthetic.main.activity_logout.*

class Logout : AppCompatActivity() {
    lateinit var sharedPref: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        sharedPref = PreferenceHelper(this)


        btn_no.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btn_yes.setOnClickListener{
            sharedPref.clear()

            startActivity(Intent(this, Landing::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_CLEAR_TOP
            })
            Toast.makeText(this,
                "You've been logged out.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}