package com.ilkom.vviped

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_categories_product.*

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