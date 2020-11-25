package com.example.vviped.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SellingPostItem (

    @SerializedName("username")
    val usernamepost: String,
    @SerializedName("user_profpict")
    val user_profpict: String,
    @SerializedName("path")
    val image_post: String,
    @SerializedName("product_name")
    val product_name: String,
    @SerializedName("product_price")
    val product_price: String,
    @SerializedName("product_desc")
    val product_description: String,
    @SerializedName("seller_loc")
    val seller_location: String,
    @SerializedName("selling_status")
    val sold: String,
    @SerializedName("whatsapp")
    val whatsapp: String,


) : Parcelable{
        val attributionUrl get() = "https://jakartaqurban.com/api/v1/search?object=$product_name"
}
