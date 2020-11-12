package com.example.vviped.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.R
import kotlinx.android.synthetic.main.notification_layout.view.*

class NotificationAdapter(
    private var context: Context,
    private var notificationList: List<NotificationItem>,
    private var isFragment: Boolean = false,
    private var onItemClickCallback: OnItemClickCallback? =null

) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val notificationTitle = itemView.findViewById<TextView>(R.id.notif_title)
        val notificationDesc = itemView.findViewById<TextView>(R.id.notif_description)
        val notificationImg = itemView.findViewById<ImageView>(R.id.notif_img)



        fun bindView(notification: NotificationItem){
            notificationTitle.text = notification.titleNotif
            notificationDesc.text = notification.descNotif
            notificationImg.setImageResource(notification.imageNotif)

            itemView.setOnClickListener{
                onItemClickCallback?.onItemClicked(notification)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.notification_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(notificationList[position])
    }

    override fun getItemCount(): Int {
        return notificationList.size

    }
    interface OnItemClickCallback{
        fun onItemClicked(data:NotificationItem)
    }
}

