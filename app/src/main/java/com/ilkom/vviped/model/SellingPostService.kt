package com.ilkom.vviped.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SellingPostService {
    @GET("api.php?apicall=sellingproducts")
    fun getFeeds(): Call<MutableList<SellingPostItem>>

    @GET("api.php?apicall=searchSellingProducts")
    fun getFeedSearch(): Call<MutableList<SellingPostItem>>

    }
