package com.mobcomp.vviped

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_categories_product.*
import kotlinx.android.synthetic.main.activity_chat_for_buying.*
import kotlinx.android.synthetic.main.activity_edit_profile_user.*
import kotlinx.android.synthetic.main.activity_edit_profile_user.backspace_button
import kotlinx.android.synthetic.main.fragment_home.*

class CategoriesProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_product)

        backspace_btn.setOnClickListener {
            onBackPressed()
        }
        val kategori = intent.getStringExtra("kategori")
        title_category_appbar.text = kategori


    }
}