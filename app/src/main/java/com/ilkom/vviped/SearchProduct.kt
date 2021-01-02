package com.ilkom.vviped

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilkom.vviped.model.*
import com.ilkom.vviped.settings.SettingsActivity
import com.ilkom.vviped.ui.HomeFragment
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchProduct : AppCompatActivity() {
    private val sellingPosts = ArrayList<SellingPostItem>()
    private var searchAdapter: SearchAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        recycleView_search.setHasFixedSize(true)
        recycleView_search.layoutManager = LinearLayoutManager(this)

        val feedsService = SellingPostRepository.create()
        feedsService.getFeeds().enqueue(object : Callback<MutableList<SellingPostItem>> {
            override fun onResponse(
                call: Call<MutableList<SellingPostItem>>,
                response: Response<MutableList<SellingPostItem>>
            ) {
                searchAdapter = getApplicationContext().let { SearchAdapter(it, response.body() as MutableList<SellingPostItem>) }
                recycleView_search.adapter = searchAdapter
                searchAdapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MutableList<SellingPostItem>>, t: Throwable) {
                Log.e("tag", t.toString())

            }

        })


        backspace_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}