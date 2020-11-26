package com.example.vviped.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SellingPostItem (

    @field:SerializedName("username")
    val usernamepost: String? = null,
    @field:SerializedName("user_profpict")
    val user_profpict: String? = null,
    @field:SerializedName("path")
    val image_post: String? = null,
    @field:SerializedName("product_name")
    val product_name: String? = null,
    @field:SerializedName("product_price")
    val product_price: String? = null,
    @field:SerializedName("product_desc")
    val product_description: String? = null,
    @field:SerializedName("seller_loc")
    val seller_location: String? = null,
    @field:SerializedName("selling_status")
    val sold: String? = null,
    @field:SerializedName("whatsapp")
    val whatsapp: String? = null,


) : Parcelable{
        val attributionUrl get() = "https://jakartaqurban.com/api/v1/search?object=$product_name"
}
