package com.ilkom.vviped.model

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface SellingPostService {
    @GET("api.php?apicall=sellingproducts")
    fun getFeeds(): Call<MutableList<SellingPostItem>>

}


