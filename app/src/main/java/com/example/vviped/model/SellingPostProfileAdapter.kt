package com.example.vviped.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.R
import com.squareup.picasso.Picasso

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