package com.ilkom.vviped.model

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Path

interface SellingPostService {
    @GET("api.php?apicall=sellingproducts")
    fun getFeeds(): Call<MutableList<SellingPostItem>>

    @GET("detect/object?={object_name}")
    fun detectedObject(@Path("object_name") nameObject: String):Call<MutableList<DetectedItem>>
}


