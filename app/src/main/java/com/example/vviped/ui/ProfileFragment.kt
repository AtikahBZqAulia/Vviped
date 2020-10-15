package com.example.vviped.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.R
import com.example.vviped.model.SellingPostItem
import com.example.vviped.model.SellingPostsAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var recyclerView: RecyclerView? = null
    private var sellingPostAdapter: SellingPostsAdapter? = null

    val sellingPosts = arrayListOf<SellingPostItem>(
        SellingPostItem("inisaya", R.drawable.profilpic, R.drawable.profilpic, "Buku Materi Matematika SMA", "Rp 70000", "Kondisi buku masih bagus.", "Bekasi, Jawa Barat"),
        SellingPostItem("inisaya", R.drawable.profilpic, R.drawable.profilpic, "Baju Atasan Lengan Panjang", "Rp 45000", "Kondisi masih sekitar 95%", "Bekasi, Jakarta Timur"),
        SellingPostItem("inisaya", R.drawable.profilpic, R.drawable.profilpic, "Komik One Piece", "Rp 20000", "Kondisi buku 70%", "Bekasi, Jawa Timur"),

        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        recyclerView = view.findViewById(R.id.recycleView_sellingPostProfile)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        sellingPostAdapter = context?.let { SellingPostsAdapter(it, sellingPosts as ArrayList<SellingPostItem>, true) }
        recyclerView?.adapter = sellingPostAdapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}