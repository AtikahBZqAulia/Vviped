package com.ilkom.vviped

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilkom.vviped.settings.SettingsActivity
import com.ilkom.vviped.ui.HomeFragment
import kotlinx.android.synthetic.main.activity_search.*

class SearchProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        backspace_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}