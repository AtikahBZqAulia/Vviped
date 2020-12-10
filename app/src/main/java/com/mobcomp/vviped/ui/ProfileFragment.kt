package com.mobcomp.vviped.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobcomp.vviped.*
import com.mobcomp.vviped.model.RetrofitInterface
import com.mobcomp.vviped.model.SellingPostItem
import com.mobcomp.vviped.model.SellingPostProfileAdapter
import com.mobcomp.vviped.model.login.Constant
import com.mobcomp.vviped.model.login.Logout
import com.mobcomp.vviped.model.login.PreferenceHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_login.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var sellingPostProfileAdapter: SellingPostProfileAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        getData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = PreferenceHelper(requireActivity())

        view.text_username.text = sharedPref.getString(Constant.PREF_USERNAME)
        view.text_fullname.text = sharedPref.getString(Constant.PREF_FULLNAME)
        view.user_email.text = sharedPref.getString(Constant.PREF_EMAIL)
        Picasso.get().load(sharedPref.getString(Constant.PREF_PROFPIC)).into(profpic)

        editprofile_btn.setOnClickListener{
            val intent = Intent(activity, EditProfileUser::class.java)
            startActivity(intent)
        }

        btn_logout.setOnClickListener{
//            onAlertDialog(view)
            val intent = Intent(activity, Logout::class.java)
            startActivity(intent)
        }

        recyclerView = view.findViewById(R.id.recycleView_profile)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)

    }

    fun getData(){

        val sharedPref = PreferenceHelper(requireActivity())

        RetrofitInterface().sellingPostProfile(
            sharedPref.getInt(Constant.PREF_ID)!!
        ).enqueue(object : Callback<MutableList<SellingPostItem>> {

            override fun onResponse(
                call: Call<MutableList<SellingPostItem>>,
                response: Response<MutableList<SellingPostItem>>
            ) {
                sellingPostProfileAdapter = context?.let { SellingPostProfileAdapter(it, response.body() as MutableList<SellingPostItem>) }
                recyclerView?.adapter = sellingPostProfileAdapter
                sellingPostProfileAdapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MutableList<SellingPostItem>>, t: Throwable) {
                Log.e("tag", t.toString())
            }
        })
    }

}



