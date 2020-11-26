package com.example.vviped.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.ChatForBuying
import com.example.vviped.MainChat
import com.example.vviped.R
import com.example.vviped.model.*
import com.example.vviped.model.login.Logout
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

    val sellingPosts = arrayListOf<SellingPostItem>(
        SellingPostItem("orang_ganteng", "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", "https://inkuiri.net/i/large/https%2Fs1.bukalapak.com%2Fimg%2F18591872112%2Flarge%2F16174c2383aa0d2c803100033a7af0bab_dijual_matematika_kelas_10.png", "Buku Materi Matematika SMA Kelas X", "Rp 70000", "Kondisi buku masih bagus.", "Bekasi, Jawa Barat", "SOLD", " "),
        SellingPostItem("lovely_suzy", "https://www.allkpop.com/upload/2020/01/content/181813/1579389188-aee70205-f9ac-4903-acb7-25d27940c050.jpeg", "https://static.republika.co.id/uploads/images/inpicture_slide/novel-terbaru-karya-tere-liye-tentang-kamu-_161026145442-103.jpg","Buku Novel Paket Tere Liye", "Rp 30000", "Kondisi buku 80%", "Rawamangun, Jakarta Timur", "SOLD", " "),
        SellingPostItem("jipyeong1808", "https://scontent-lga3-2.cdninstagram.com/v/t51.2885-15/e35/c58.0.566.566a/122471859_370870834108333_3309400621282386062_n.jpg?_nc_ht=scontent-lga3-2.cdninstagram.com&_nc_cat=101&_nc_ohc=kcH_Yo4Hq7IAX_vZ3jd&oh=556626af413ded35b8761be2bb749ae5&oe=5FC06ECC", "https://cf.shopee.co.id/file/34ae00a0870401a784b36f195a7dc6b0","T-Shirt Mentor Han Ji Pyeong Start-up", "Rp 80000", "Kondisi hoodie masih sekitar 90%", "Malang, Jawa Timur", "SOLD", " "),

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

        getData()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



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