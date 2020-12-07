package com.mobcomp.vviped

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobcomp.vviped.model.SellingPostItem
import com.mobcomp.vviped.model.SellingPostRepository
import com.mobcomp.vviped.model.SellingPostsAdapter
import com.mobcomp.vviped.ui.HomeFragment
import kotlinx.android.synthetic.main.fragment_edit_profile.backspace_btn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FilteredCategoriesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var recyclerView: RecyclerView? = null
    private var sellingPostAdapter: SellingPostsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // in here you can do logic when backPress is clicked
                onBackPressed()
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_filtered_categories, container, false)
        getData()
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backspace_btn.setOnClickListener {
            onBackPressed()
        }

        recyclerView = view.findViewById(R.id.recycleView_home)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)



    }

    fun onBackPressed() {
        (context as MainActivity).changeFragment(HomeFragment())
    }

    fun getData(){

        val feedsService = SellingPostRepository.create()
        feedsService.getFeeds().enqueue(object : Callback<MutableList<SellingPostItem>> {
            override fun onResponse(
                call: Call<MutableList<SellingPostItem>>,
                response: Response<MutableList<SellingPostItem>>
            ) {
                sellingPostAdapter = context?.let { SellingPostsAdapter(it, response.body() as MutableList<SellingPostItem>) }
                recyclerView?.adapter = sellingPostAdapter
                sellingPostAdapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MutableList<SellingPostItem>>, t: Throwable) {
                Log.e("tag", t.toString())
            }
        })
    }

}