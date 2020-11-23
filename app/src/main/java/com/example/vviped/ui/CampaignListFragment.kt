package com.example.vviped.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.*
import com.example.vviped.model.*
import kotlinx.android.synthetic.main.fragment_campaign_list.*
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CampaignListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CampaignListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var recyclerView: RecyclerView? = null
    private var campaignListAdapter: CampaignListAdapter? = null
    val url = "https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png"
    val campaignLists = arrayListOf<CampaignItem>(
        CampaignItem("Kitabisa.com", "https://avatars0.githubusercontent.com/u/40655294?s=200&v=4", "https://kitabisa-userupload-01.s3-ap-southeast-1.amazonaws.com/_production/campaign/97098/31_97098_1545812068_232891_f.png","Bantu Pak Somat Mengobati Kanker", "desc here", "Bekasi, Jawa Barat"),
        CampaignItem("Kitabisa.com", "https://avatars0.githubusercontent.com/u/40655294?s=200&v=4", "https://kitabisa-userupload-01.s3-ap-southeast-1.amazonaws.com/_production/campaign/128602/31_104915_1549273040_63962_f.jpg","Bantu Soni untuk Sekolah", "desc here", "Rawamangun, Jakarta Timur"),
        CampaignItem("Kitabisa.com", "https://avatars0.githubusercontent.com/u/40655294?s=200&v=4", "https://kitabisa-userupload-01.s3-ap-southeast-1.amazonaws.com/_production/campaign/95361/31_95361_1545362552_579425_f.PNG","Bantu Ibu Jumilah Mengobati Kakinya", "desc here", "Malang, Jawa Timur"),
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_campaign_list, container, false)

        recyclerView = view.findViewById(R.id.recycleView_campaign)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        campaignListAdapter = context?.let { CampaignListAdapter(it, campaignLists as ArrayList<CampaignItem>, true) }
        recyclerView?.adapter = campaignListAdapter

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_create_campaign.setOnClickListener{
            val intent = Intent(activity, create_campaign::class.java)
            startActivity(intent)
        }

        campaignListAdapter?.setOnItemClickCallback(object : CampaignListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: CampaignItem) {
//                Toast.makeText(getActivity()?.getBaseContext(), "notification: "+ data.descNotif, Toast.LENGTH_SHORT).show()

                val intent = Intent(getActivity(), UploadSellingActivity::class.java)
                intent.putExtra("title_campaign",data.campaign_name)
                startActivity(intent)
            }
        })


    }
}
