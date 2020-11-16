package com.example.vviped.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.Landing
import com.example.vviped.MainChat
import com.example.vviped.R
import com.example.vviped.model.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
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
    private var recyclerView: RecyclerView? = null
    private var sellingPostAdapter: SellingPostsAdapter? = null

    val sellingPosts = arrayListOf<SellingPostItem>(
        SellingPostItem("inisaya", R.drawable.profilpic, R.drawable.profilpic, "Buku Materi Matematika SMA", "Rp 70000", "Kondisi buku masih bagus.", "Bekasi, Jawa Barat", "SOLD"),
        SellingPostItem("akunbaru", R.drawable.profilpic, R.drawable.profilpic, "Buku Novel Andaikan Aku Menjadi", "Rp 30000", "Kondisi buku 80%", "Rawamangun, Jakarta Timur", "SOLD"),
        SellingPostItem("anakbaik", R.drawable.profilpic, R.drawable.profilpic, "Hoodie White", "Rp 80000", "Kondisi hoodie masih sekitar 90%", "Malang, Jawa Timur", "SOLD"),

        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)



        recyclerView = view.findViewById(R.id.recycleView_home)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        sellingPostAdapter = context?.let { SellingPostsAdapter(it, sellingPosts as ArrayList<SellingPostItem>, true) }
        recyclerView?.adapter = sellingPostAdapter
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
        feedsService.getFeeds().enqueue(object : Callback<List<SellingPostItem>> {
            override fun onResponse(
                call: Call<List<SellingPostItem>>,
                response: Response<List<SellingPostItem>>
            ) {
                val data = response.body()
                Log.d("tag", ":responsenya ${data?.size}")
                data?.map {
                    Log.d("tag", "datanya ${it.product_price}")
//                    val d = Log.d("tag", "descnya ${it.product_description}")
                    recyclerView?.adapter = sellingPostAdapter
                    sellingPostAdapter?.notifyDataSetChanged()
                }
//                campaignListAdapter = context?.let { CampaignListAdapter1(it, data as List<CampaignItem>, true) }


            }

            override fun onFailure(call: Call<List<SellingPostItem>>, t: Throwable) {
                Log.e("tag", t.toString())
            }
        })
    }

}
