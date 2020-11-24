package com.example.vviped.model

import retrofit2.Call
import retrofit2.http.GET

interface SellingPostService {
    @GET("api.php?apicall=sellingproducts")
    fun getFeeds(): Call<ArrayList<SellingPostItem>>
}