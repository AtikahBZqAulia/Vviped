package com.example.vviped.model.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

            val intent = Intent(this, Landing::class.java)
            startActivity(intent)
            Toast.makeText(this,
                "You've been logged out.",
                Toast.LENGTH_SHORT
            ).show()
        }

        fullname.text = sharedPref.getString(Constant.PREF_USERNAME)
    }
}