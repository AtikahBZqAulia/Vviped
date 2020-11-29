package com.example.vviped.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.MainChat
import com.example.vviped.NotificationDetails
import com.example.vviped.R
import com.example.vviped.model.NotificationAdapter
import com.example.vviped.model.NotificationItem
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_notification.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var recyclerView: RecyclerView? = null
    private var notificationAdapter: NotificationAdapter? = null

    val notificationList = arrayListOf<NotificationItem>(
        NotificationItem("You have a request", "This is notification description2", R.drawable.ic_notifications),

        )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        recyclerView = view.findViewById(R.id.recyclerView_notification)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)


        notificationAdapter = context?.let { NotificationAdapter(it, notificationList as ArrayList<NotificationItem>) }
        recyclerView?.adapter = notificationAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_insertItem.setOnClickListener{
            insertNotif(view)
        }
        notificationAdapter?.setOnItemClickCallback(object : NotificationAdapter.OnItemClickCallback {
            override fun onItemClicked(data: NotificationItem) {
//                Toast.makeText(getActivity()?.getBaseContext(), "notification: "+ data.descNotif, Toast.LENGTH_SHORT).show()

                val intent = Intent(getActivity(), NotificationDetails::class.java)
                intent.putExtra("details",data.descNotif )
                startActivity(intent)

            }
        })


    }
    fun insertNotif(view: View){
        val newItem =  NotificationItem(
            "You have a request",
            "ada yang order barang kamu nich. Klik untuk konfirmasi",
            R.drawable.ic_upload_img
        )
        notificationList.add(newItem)
        notificationAdapter?.notifyDataSetChanged()
    }


}