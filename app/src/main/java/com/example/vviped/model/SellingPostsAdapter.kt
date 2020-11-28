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
import com.example.vviped.ChatForBuying
import com.example.vviped.R
import com.squareup.picasso.Picasso

class SellingPostsAdapter(
    private val context: Context,
    private val sellingPosts: MutableList<SellingPostItem>
) : RecyclerView.Adapter<SellingPostsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val usernamepost = itemView.findViewById<TextView>(R.id.username_post)
        val profpictpost = itemView.findViewById<ImageView>(R.id.user_profpict_post)
        val imagepost = itemView.findViewById<ImageView>(R.id.imagepost_layout)
        val productname = itemView.findViewById<TextView>(R.id.product_name)
        val productcondition = itemView.findViewById<TextView>(R.id.produk_kondisi)
        val campaignname = itemView.findViewById<TextView>(R.id.campaign_title)
        val productprice = itemView.findViewById<TextView>(R.id.harga_produk)
        val productdesc = itemView.findViewById<TextView>(R.id.produk_deskripsi)
        val sellerlocation = itemView.findViewById<TextView>(R.id.lokasi_penjual)
        val buyButton = itemView.findViewById<Button>(R.id.btn_buy)
        val soldTextView = itemView.findViewById<TextView>(R.id.sold_tv)
        val whatsappNumber = itemView.findViewById<TextView>(R.id.whatsapp_number)

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
            whatsappNumber.text = sellingPost.whatsapp

            val seller_username = usernamepost.text.toString()
            val product_name = productname.text.toString()
            val product_price =  productprice.text.toString()
            val whatsappNumber = whatsappNumber.text.toString()


            buyButton.setOnClickListener {
                val context = buyButton.context
                val intent = Intent(context, ChatForBuying::class.java)

                intent.putExtra("seller_username", seller_username )
                intent.putExtra("product_name", product_name )
                intent.putExtra("product_price", product_price )
                intent.putExtra("whatsapp", whatsappNumber )


                context.startActivity(intent)



            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.sellingposts_layout, parent, false)
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
        viewHolder.whatsappNumber.text = sellingPosts[position].whatsapp
        viewHolder.bindView(sellingPosts[position])

    }

    override fun getItemCount(): Int {
        return sellingPosts.size
    }
}

