package com.ilkom.vviped.model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.ilkom.vviped.R
import com.squareup.picasso.Picasso
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

            //menu  edit dan hapus product post
            buttonContextMenu.setOnClickListener {
                val context = buttonContextMenu.context
                val pop= PopupMenu(context, it)
                pop.inflate(R.menu.context_menu_post)
                pop.setOnMenuItemClickListener { item ->
                    when(item.itemId){
                        R.id.menu_edit_post->{
                            Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show()

                        }
                        R.id.menu_delete_post->{
                            Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()

                        }
                    }
                    true
                }
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