package com.example.vviped

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.vviped.model.login.Constant
import com.example.vviped.model.login.PreferenceHelper
import com.example.vviped.ui.CampaignListFragment
import com.example.vviped.ui.HomeFragment
import com.example.vviped.ui.NotificationFragment
import com.example.vviped.ui.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private var homeFragment = HomeFragment()
    private var profileFragment = ProfileFragment()
    private var campaignListFrament = CampaignListFragment()
    private var notificationFragment = NotificationFragment()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(this)

    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_layout, homeFragment).commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout, homeFragment).commit()
                return true
            }
            R.id.navigation_campaignlist -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout, campaignListFrament).commit()
                return true
            }

            R.id.navigation_notification -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout, notificationFragment).commit()
                return true
            }
            R.id.navigation_profile -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout, profileFragment).commit()
                return true
            }
            else -> return false

        }
    }

    fun changeFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_layout, fragment)
        transaction.commit()
    }

}

