package com.example.vviped

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import androidx.viewpager.widget.ViewPager
import com.example.vviped.model.OnBoardingData
import com.google.android.material.tabs.TabLayout

class IntroSlider : AppCompatActivity() {

    var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoardingViewPager: ViewPager? = null
    var nextSlide: TextView? = null
    var position = 0
    var sharedPreferences: SharedPreferences? =  null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(restorePrefData()) {
            val i = Intent(applicationContext, Landing::class.java)
            startActivity(i)
        }

        setContentView(R.layout.activity_intro_slider)

        tabLayout = findViewById(R.id.tab_indicator)
        nextSlide = findViewById(R.id.next_slide)
        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
         onBoardingData.add(OnBoardingData(
            "WELCOME TO VVIPED",
            "List your products to sell it, and the profit should be donated to a charity campaign you made. " +
                    "or you can choose others campaign too!",
            R.drawable.logoapp))
        onBoardingData.add(OnBoardingData(
            "PURCHASE THE PRODUCTS TO DONATE THE CAMPAIGN",
            "You can be both seller or buyer. " +
                    "Get notified by a message if someone interested in your product, deal with the transaction, and finish the payment." ,
            R.drawable.logoapp))
        onBoardingData.add(OnBoardingData(
            "GETTING STARTED IS EASY",
            "By getting started, you already took the biggest step in sharing your kindness to help each other. ",
            R.drawable.logoapp))

        setOnBoardingViewPagerAdapter(onBoardingData)

        position = onBoardingViewPager!!.currentItem

        nextSlide?.setOnClickListener {

            if (position < onBoardingData.size) {
                position++
                onBoardingViewPager!!.currentItem = position
            }
            if(position == onBoardingData.size){
                savePrefData()
                val i = Intent(applicationContext, Landing::class.java)
                startActivity(i)
            }
            tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    position = tab!!.position
                    if (tab.position == onBoardingData.size - 1) {
                        nextSlide!!.text = "Get Started"
                    } else {
                        nextSlide!!.text = "Next"

                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })


        }

    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: MutableList<OnBoardingData>) {
        onBoardingViewPager = findViewById(R.id.screenPager)
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoardingViewPager!!.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)
    }

    private fun savePrefData(){
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    private fun restorePrefData(): Boolean{
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("isFirstTimeRun", false)

    }

}

