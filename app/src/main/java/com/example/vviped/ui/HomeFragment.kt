package com.example.vviped.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vviped.MainChat
import com.example.vviped.R
import com.example.vviped.model.SellingPostItem
import com.example.vviped.model.SellingPostRepository
import com.example.vviped.model.SellingPostsAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val sellingposts = ArrayList<SellingPostItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recycleView_home.setHasFixedSize(true)
        recycleView_home.layoutManager = LinearLayoutManager(context)

        getData()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_chat.setOnClickListener{
            val intent = Intent(activity, MainChat::class.java)
            startActivity(intent)
        }
    }

    fun getData(){
        val feedsService = SellingPostRepository.create()
        feedsService.getFeeds().enqueue(object : Callback<ArrayList<SellingPostItem>> {
            override fun onResponse(
                call: Call<ArrayList<SellingPostItem>>,
                response: Response<ArrayList<SellingPostItem>>
            ) {
                response.body()?.let { sellingposts.addAll(it) }
                val adapter = SellingPostsAdapter(sellingposts)
                recycleView_home.adapter = adapter
                }

            override fun onFailure(call: Call<ArrayList<SellingPostItem>>, t: Throwable) {
            }
//                campaignListAdapter = context?.let { CampaignListAdapter1(it, data as List<CampaignItem>, true) }

        })
    }
}
