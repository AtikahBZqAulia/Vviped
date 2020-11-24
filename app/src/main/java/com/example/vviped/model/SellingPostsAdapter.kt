package com.example.vviped.model

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.MainChat
import com.example.vviped.R
import kotlinx.android.synthetic.main.sellingposts_layout.view.*

class SellingPostsAdapter(
    private var sellingPosts: ArrayList<SellingPostItem>,
) : RecyclerView.Adapter<SellingPostsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

     //   val usernamepost = itemView.findViewById<TextView>(R.id.username_post)
     //   val profpictpost = itemView.findViewById<ImageView>(R.id.user_profpict_post)
     //   val imagepost = itemView.findViewById<ImageView>(R.id.imagepost_layout)

        fun bindView(sellingPost: SellingPostItem) {
            with(itemView) {
                val productprice = sellingPost.product_price
                val productname = sellingPost.product_name
                val productcondition = sellingPost.product_condition
                val productdesc = sellingPost.product_description
                val sellerlocation = sellingPost.seller_location
            //    val soldTextView = sellingPost.sold

                product_name.text = productname
                harga_produk.text = productprice
                produk_kondisi.text = productcondition
                produk_deskripsi.text = productdesc
                lokasi_penjual.text = sellerlocation
            //    sold_tv.text = soldTextView

                btn_buy.setOnClickListener() {
                    val context = btn_buy.context
                    val intent = Intent(context, MainChat::class.java)
                    context.startActivity(intent)
                }
            }
      //      usernamepost.text = sellingPost.usernamepost
      //      profpictpost.setImageResource(sellingPost.user_profpict)
      //      imagepost.setImageResource(sellingPost.image_post)

         }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellingPostsAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.sellingposts_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: SellingPostsAdapter.ViewHolder, position: Int) {
        viewHolder.bindView(sellingPosts[position])

    }

    override fun getItemCount(): Int {
        return sellingPosts.size
    }





}
