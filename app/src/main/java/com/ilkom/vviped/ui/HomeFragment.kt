package com.ilkom.vviped.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilkom.vviped.*
import com.ilkom.vviped.model.*
import com.ilkom.vviped.settings.SettingsActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.sellingposts_layout.*
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        recyclerView = view.findViewById(R.id.recycleView_home)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        getData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            search_et.setOnClickListener {
                val intent = Intent(activity, SearchProduct::class.java)
                startActivity(intent)
            }

//        search_edit_text.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
//            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
//                (context as MainActivity).changeFragment(SearchFragment())
//                v.hideKeyboard()
//                v.clearFocus()
//                return@OnKeyListener true
//            }
//            false
//        })

        kategori1.setOnClickListener {
            val tv_kategori1 = kategori1.text.toString()
            val context = kategori1.context
            val intent = Intent(context, CategoriesProduct::class.java)
            intent.putExtra("kategori", tv_kategori1 )
            context.startActivity(intent)
        }

        kategori2.setOnClickListener {
            val tv_kategori2 = kategori2.text.toString()
            val context = kategori2.context
            val intent = Intent(context, CategoriesProduct::class.java)
            intent.putExtra("kategori", tv_kategori2 )
            context.startActivity(intent)
        }

        kategori3.setOnClickListener {
            val tv_kategori3 = kategori3.text.toString()
            val context = kategori3.context
            val intent = Intent(context, CategoriesProduct::class.java)
            intent.putExtra("kategori", tv_kategori3 )
            context.startActivity(intent)
        }


        kategori4.setOnClickListener {
            val tv_kategori4 = kategori4.text.toString()
            val context = kategori4.context
            val intent = Intent(context, CategoriesProduct::class.java)
            intent.putExtra("kategori", tv_kategori4 )
            context.startActivity(intent)
        }


        kategori5.setOnClickListener {
            val tv_kategori5 = kategori5.text.toString()
            val context = kategori5.context
            val intent = Intent(context, CategoriesProduct::class.java)
            intent.putExtra("kategori", tv_kategori5 )
            context.startActivity(intent)
        }

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

                refreshTabHomeProduct()
                if(progressBarTabHome != null) {
                    progressBarTabHome.visibility = View.GONE
                } else {
                    progressBarTabHome.visibility = View.VISIBLE
                }


            }

            override fun onFailure(call: Call<MutableList<SellingPostItem>>, t: Throwable) {
                Log.e("tag", t.toString())
            }
        })
    }

    private fun refreshTabHomeProduct() {
        swipeRefreshTabHomeProduct.setOnRefreshListener {
            getData()
            swipeRefreshTabHomeProduct.isRefreshing = false
        }
    }


    fun View.hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)

    }


}
