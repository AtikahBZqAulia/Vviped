package com.example.vviped.model

import android.os.Parcelable
import com.example.vviped.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SellingPostItem (
    val usernamepost: String,
    val user_profpict: Int,
    val image_post: Int,
    val product_name: String,
    val product_price: String,
    val product_description: String,
    val seller_location: String,
    val sold: String
) : Parcelable{
        val attributionUrl get() = "http://127.0.0.1:5000/api/v1/search?object=$product_name"
}
