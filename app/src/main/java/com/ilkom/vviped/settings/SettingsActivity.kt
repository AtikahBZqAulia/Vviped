package com.ilkom.vviped.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilkom.vviped.EditProfileUser
import com.ilkom.vviped.R
import com.ilkom.vviped.model.login.Logout
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        btn_settings_about.setOnClickListener{
            val intent = Intent(this, SettingsAbout::class.java)
            startActivity(intent)
        }

        btn_settings_contact.setOnClickListener{
            val intent = Intent(this, SettingsContactUs::class.java)
            startActivity(intent)
        }

        btn_settings_logout.setOnClickListener{
            val intent = Intent(this, Logout::class.java)
            startActivity(intent)
        }
        btn_settings_edit_profile.setOnClickListener {
            val intent = Intent(this, EditProfileUser::class.java)
            startActivity(intent)

        }


    }
}