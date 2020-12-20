package com.ilkom.vviped.model

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.ilkom.vviped.R
import com.ilkom.vviped.UploadSellingActivity
import com.ilkom.vviped.model.login.Constant
import com.ilkom.vviped.model.login.Constant.Companion.PREF_ID
import com.ilkom.vviped.model.login.PreferenceHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile_user.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SellingPostProfileAdapter(
    private val context: Context,
    private val sellingPosts: MutableList<SellingPostItem>


) : RecyclerView.Adapter<SellingPostProfileAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val usernamepost = itemView.findViewById<TextView>(R.id.usernamepost)
        val profpictpost = itemView.findViewById<ImageView>(R.id.userprofpict_post)
        val imagepost = itemView.findViewById<ImageView>(R.id.imagepostlayout)
        val productname = itemView.findViewById<TextView>(R.id.productname)
        val productcondition = itemView.findViewById<TextView>(R.id.produkkondisi)
        val campaignname = itemView.findViewById<TextView>(R.id.campaigntitle)
        val productprice = itemView.findViewById<TextView>(R.id.hargaproduk)
        val productdesc = itemView.findViewById<TextView>(R.id.produkdeskripsi)
        val sellerlocation = itemView.findViewById<TextView>(R.id.lokasipenjual)
        val soldTextView = itemView.findViewById<TextView>(R.id.sale_tv)
        val buttonContextMenu = itemView.findViewById<ImageButton>(R.id.btn_context_menu)


        fun bindView(sellingPost: SellingPostItem) {
            usernamepost.text = sellingPost.usernamepost
            Picasso.get().load(sellingPost.user_profpict).into(profpictpost)
            Picasso.get().load(sellingPost.image_post).into(imagepost)
            productname.text = sellingPost.product_name
            productcondition.text = sellingPost.product_condition
            campaignname.text = sellingPost.campaign_title
            productprice.text = sellingPost.product_price
            productdesc.text = sellingPost.product_description
            sellerlocation.text = sellingPost.seller_location
            soldTextView.text = sellingPost.sold

            val sharedPref = PreferenceHelper(itemView.context)

            val product_id = sellingPost.id


            val product_name = productname.text.toString()
            val product_price =  productprice.text.toString()
            val campaign_title = campaignname.text.toString()

            //menu  edit dan hapus product post
            buttonContextMenu.setOnClickListener {

                val context = buttonContextMenu.context

                val pop= PopupMenu(context, it)
                pop.inflate(R.menu.context_menu_post)
                pop.setOnMenuItemClickListener { item ->
                    when(item.itemId){
                        R.id.menu_edit_post->{
                            Toast.makeText(context, "Ubah", Toast.LENGTH_SHORT).show()

                        }
                        R.id.menu_delete_post->{
                            Toast.makeText(context, "hapus id:" + product_id, Toast.LENGTH_SHORT).show()

                            RetrofitInterface().deleteProductProfile(
                                sharedPref.getInt(Constant.PREF_ID)!!
                            ).enqueue(object : Callback<MutableList<SellingPostItem>> {

                                override fun onFailure(call: Call<MutableList<SellingPostItem>>, t: Throwable) {
                                    Toast.makeText(context, "Gagal dihapus", Toast.LENGTH_SHORT).show()

                                }

                                override fun onResponse(
                                    call: Call<MutableList<SellingPostItem>>,
                                    response: Response<MutableList<SellingPostItem>>
                                ) {
                                    Toast.makeText(context, "Berhasil dihapus", Toast.LENGTH_SHORT).show()

                                }
                            })
                        }
                        R.id.menu_share_post->{
                            Toast.makeText(context, "Bagikan", Toast.LENGTH_SHORT).show()
                            val context = buttonContextMenu.context
                            val shareIntent = Intent()
                            shareIntent.action = Intent.ACTION_SEND
                            shareIntent.type="text/plain"
                            shareIntent.putExtra(
                                Intent.EXTRA_TEXT,
                                "Bantu berdonasi dengan membeli barang: $product_name " +
                                        "seharga Rp$product_price " +
                                        "guna mendukung galang dana untuk campaign: $campaign_title. " +
                                        "Beli barangnya sekarang juga hanya di Vviped! "
                            )
                            val sendIntent = Intent.createChooser(shareIntent, null)
                            context.startActivity(sendIntent)
                        }
                    }
                    true
                }
                // munculin icon
                try {
                    val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                    fieldMPopup.isAccessible = true
                    val mPopup = fieldMPopup.get(pop)
                    mPopup.javaClass
                        .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                        .invoke(mPopup, true)
                } catch (e: Exception){
                    Log.e("Main", "Error showing menu icons", e )

                } finally {
                    pop.show()
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.sellingpostprofile_layout, parent, false)
        return ViewHolder(v)



    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.usernamepost.text = sellingPosts[position].usernamepost
        Picasso.get().load(sellingPosts[position].user_profpict).into(viewHolder.profpictpost)
        Picasso.get().load(sellingPosts[position].image_post).into(viewHolder.imagepost)
        viewHolder.productname.text = sellingPosts[position].product_name
        viewHolder.productprice.text = sellingPosts[position].product_price
        viewHolder.productdesc.text = sellingPosts[position].product_description
        viewHolder.sellerlocation.text = sellingPosts[position].seller_location
        viewHolder.soldTextView.text = sellingPosts[position].sold
        viewHolder.bindView(sellingPosts[position])




    }

    override fun getItemCount(): Int {
        return sellingPosts.size
    }
}


