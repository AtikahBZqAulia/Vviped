package com.example.vviped.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.R
import com.example.vviped.UploadSellingActivity
import com.squareup.picasso.Picasso

class CampaignListAdapter(
    private var context: Context,
    private var campaignLists: MutableList<CampaignModel>,
    private var isFragment: Boolean = false,
    private var onItemClickCallback: CampaignListAdapter.OnItemClickCallback? =null
) : RecyclerView.Adapter<CampaignListAdapter.ViewHolder>() {

    fun setOnItemClickCallback(onItemClickCallback: CampaignListAdapter.OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val organizationnamepost = itemView.findViewById<TextView>(R.id.organizationname_post)
        val organizationprofpict_post = itemView.findViewById<ImageView>(R.id.organization_profpict_post)
        val imagecampaign = itemView.findViewById<ImageView>(R.id.imagecampaign_layout)
        val campaignname = itemView.findViewById<TextView>(R.id.campaign_name)
        val campaigndesc = itemView.findViewById<TextView>(R.id.campaign_deskripsi)
        val campaigncategory = itemView.findViewById<TextView>(R.id.campaign_category)
        val campaignreceiver = itemView.findViewById<TextView>(R.id.campaign_penerima)
        val usagedetails = itemView.findViewById<TextView>(R.id.usage_detail)
        val donatebyselling = itemView.findViewById<Button>(R.id.donatewithselling_btn)

        fun bindView(campaignItem: CampaignModel) {
            organizationnamepost.text = campaignItem.organization_name
            Picasso.get().load(campaignItem.organization_profpict).into(organizationprofpict_post)
            Picasso.get().load(campaignItem.image_campaign).into(imagecampaign)
            campaignname.text = campaignItem.campaign_title
            campaigndesc.text = campaignItem.campaign_desc
            campaigncategory.text = campaignItem.campaign_category
            campaignreceiver.text = campaignItem.donation_goes
            usagedetails.text = campaignItem.usage_details

            val campaign_id = campaignItem.id

            donatebyselling.setOnClickListener() {
                val context = donatebyselling.context
                val intent = Intent(context, UploadSellingActivity::class.java)

                intent.putExtra("title_campaign", campaignname.text.toString() )
                intent.putExtra("campaign_id", campaign_id )

                context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CampaignListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.campaign_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return campaignLists.size
    }

    override fun onBindViewHolder(viewHolder: CampaignListAdapter.ViewHolder, position: Int) {
        viewHolder.bindView(campaignLists[position])
    }

    interface OnItemClickCallback{
        fun onItemClicked(data:CampaignModel)
    }
}
