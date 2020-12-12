package com.mobcomp.vviped

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mobcomp.vviped.model.login.Constant
import com.mobcomp.vviped.model.login.PreferenceHelper
import com.mobcomp.vviped.model.OnBoardingData

class IntroSlider : AppCompatActivity() {

    var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoardingViewPager: ViewPager? = null
    var nextSlide: TextView? = null
    var position = 0
    var sharedPreferences: SharedPreferences? =  null
    lateinit var sharedPref: PreferenceHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = PreferenceHelper(this)


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
            "VVIPED adalah aplikasi media sosial untuk penggalangan dana secara online " +
                    "dengan cara jual-beli barang sekaligus berdonasi.",
            R.drawable.logoapp))
        onBoardingData.add(OnBoardingData(
            "SALE TO DONATE",
            "Kamu bisa menjual produk yang masih layak pakai atau produk kamu sendiri dan hasil penjualannya akan disumbangkan " +
                    "untuk penggalangan dana yang bisa kamu buat sendiri ataupun ikut dengan galang dana milik orang lain." ,
            R.drawable.logoapp))
        onBoardingData.add(OnBoardingData(
            "HOW TO SALE?",
            "Tekan tombol 'Jual Produk Saya' pada penggalangan dana yang ingin kamu donasikan yang ada pada halaman Daftar Campaign untuk menjual produk kamu",
            R.drawable.logoapp))
        onBoardingData.add(OnBoardingData(
            "HOW TO MAKE CAMPAIGN?",
            "Tekan tombol tanda tambah yang ada pada halaman Daftar Campaign untuk membuat penggalangan dana",
            R.drawable.logoapp))
        onBoardingData.add(OnBoardingData(
            "GETTING STARTED IS EASY",
            "Bergabung dengan Vviped, kamu sudah mengambil langkah besar dalam berbagi kebaikan untuk saling membantu!",
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
                        nextSlide!!.text = "MULAI"
                    } else {
                        nextSlide!!.text = "Selanjutnya"

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

