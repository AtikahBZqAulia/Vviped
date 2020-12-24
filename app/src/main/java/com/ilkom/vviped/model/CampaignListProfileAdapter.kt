package com.ilkom.vviped.model

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.ilkom.vviped.R
import com.squareup.picasso.Picasso
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val buttonContextMenuCampaign = itemView.findViewById<ImageButton>(R.id.btn_context_menu_campaign)


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
            val campaignId = campaign_id.toString()
            val campaign_name = campaignname.text.toString()


            //menu  edit, hapus, share product campaign
            buttonContextMenuCampaign.setOnClickListener {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()

//                val context = buttonContextMenuCampaign.context
//                val popCampaign = PopupMenu(context, it)
//                popCampaign.inflate(R.menu.context_menu_campaign)

                val context = buttonContextMenuCampaign.context
                val pop = PopupMenu(context, it)
                pop.inflate(R.menu.context_menu_campaign)

                pop.setOnMenuItemClickListener { item ->
                    Toast.makeText(context, "clicked dalem", Toast.LENGTH_SHORT).show()

                    when(item.itemId){
                        R.id.menu_edit_campaign->{
//                            Toast.makeText(context, "Ubah", Toast.LENGTH_SHORT).show()

                        }
                        R.id.menu_delete_campaign->{

//                            AlertDialog.Builder(context)
//
//                                .setMessage("Hapus campaign ini?")
//                                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialogInterface, i ->
//
//                                    RetrofitInterface().deleteCampaignProfile(
//                                        RequestBody.create(
//                                            MediaType.parse("multipart/form-data"),
//                                            campaignId
//                                        ),
//                                    ).enqueue(object : Callback<MutableList<CampaignModel>> {
//
//                                        override fun onFailure(call: Call<MutableList<CampaignModel>>, t: Throwable) {
//                                            Toast.makeText(context, "Gagal dihapus.", Toast.LENGTH_SHORT).show()
//                                        }
//                                        override fun onResponse(
//                                            call: Call<MutableList<CampaignModel>>,
//                                            response: Response<MutableList<CampaignModel>>
//                                        ) {
//                                            Toast.makeText(context, "Berhasil dihapus", Toast.LENGTH_SHORT).show()
//                                        }
//                                    })
//                                })
//                                .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
//                                })
//                                .show()

                        }
                        R.id.menu_share_campaign->{
//                            val context = buttonContextMenuCampaign.context
//                            val shareIntent = Intent()
//                            shareIntent.action = Intent.ACTION_SEND
//                            shareIntent.type="text/plain"
//                            shareIntent.putExtra(
//                                Intent.EXTRA_TEXT,
//                                "Saya mendukung campaign: $campaign_name. " +
//                                        "Bantu donasi dengan menjual barang kamu atau membeli barang untuk " +
//                                        "ikut mendukung penggalangan dana ini hanya di Vviped! ( http://bit.ly/Vviped_App )"
//                            )
//                            val sendIntent = Intent.createChooser(shareIntent, "Bagikan product ini ")
//                            context.startActivity(sendIntent)
                        }
                    }
                    true
                }

            }


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