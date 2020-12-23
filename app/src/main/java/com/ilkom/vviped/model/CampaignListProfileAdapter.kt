package com.ilkom.vviped.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilkom.vviped.R
import com.squareup.picasso.Picasso

class CampaignListProfileAdapter(
    private var context: Context,
    private var campaignLists: MutableList<CampaignModel>,
) : RecyclerView.Adapter<CampaignListProfileAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val organizationnamepost = itemView.findViewById<TextView>(R.id.organizationname_post)
        val organizationprofpict_post = itemView.findViewById<ImageView>(R.id.organization_profpict_post)
        val imagecampaign = itemView.findViewById<ImageView>(R.id.imagecampaign_layout)
        val campaignname = itemView.findViewById<TextView>(R.id.campaign_name)
        val campaigndesc = itemView.findViewById<TextView>(R.id.campaign_deskripsi)
        val campaigncategory = itemView.findViewById<TextView>(R.id.campaign_category)
        val campaignreceiver = itemView.findViewById<TextView>(R.id.campaign_penerima)
        val usagedetails = itemView.findViewById<TextView>(R.id.usage_detail)

        fun bindView(campaignItem: CampaignModel) {
            organizationnamepost.text = campaignItem.organization_name
            Picasso.get().load(campaignItem.organization_profpict).into(organizationprofpict_post)
            Picasso.get().load(campaignItem.image_campaign).into(imagecampaign)
            campaignname.text = campaignItem.campaign_title
            campaigndesc.text = campaignItem.campaign_desc
            campaigncategory.text = campaignItem.campaign_category
            campaignreceiver.text = campaignItem.donation_goes
            usagedetails.text = campaignItem.usage_details

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CampaignListProfileAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.campaignpostprofile_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return campaignLists.size
    }

    override fun onBindViewHolder(viewHolder: CampaignListProfileAdapter.ViewHolder, position: Int) {
        viewHolder.bindView(campaignLists[position])
    }

}