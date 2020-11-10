package com.example.vviped.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.R
import com.example.vviped.model.NotificationAdapter
import com.example.vviped.model.NotificationItem

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
        NotificationItem("You have a request", "This is notification description1This is notification description1This is notification description1", R.drawable.profilpic),
        NotificationItem("You have a request", "This is notification description2", R.drawable.ic_notifications),
        NotificationItem("You have a request", "This is notification description3", R.drawable.profilpic),
        NotificationItem("You have a request", "This is notification description4", R.drawable.profilpic),
        NotificationItem("You have a request", "This is notification description5", R.drawable.profilpic),

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


}