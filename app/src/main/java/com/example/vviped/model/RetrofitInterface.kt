package com.example.vviped.model

import com.example.vviped.model.login.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RetrofitInterface {
    @Multipart
    @POST("api.php?apicall=upload")
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("product_price") product_price: RequestBody,
        @Part("product_name") product_name: RequestBody,
        @Part("product_condition") product_condition: RequestBody,
        @Part("product_desc") product_desc: RequestBody,
        @Part("seller_loc") seller_loc: RequestBody,
        @Part("selling_status") selling_status: RequestBody
    ): Call<UploadResponse>

    @Multipart
    @POST("api.php?apicall=uploadCampaign")
    fun uploadCampaign(
        @Part image: MultipartBody.Part,
        @Part("campaign_category") campaign_category: RequestBody,
        @Part("campaign_title") campaign_title: RequestBody,
        @Part("campaign_desc") campaign_desc: RequestBody,
        @Part("donation_goes") donation_goes: RequestBody,
        @Part("usage_details") usage_details: RequestBody,
        @Part("phone_number") phone_number: RequestBody,
    ): Call<UploadResponse>

    @Multipart
    @POST("api.php?apicall=register")
    fun registerUser(
        @Part("email") email: RequestBody,
        @Part("fullname") fullname: RequestBody,
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
    ): Call<UploadResponse>

    @Multipart
    @POST("api.php?apicall=login")
    fun loginUser(
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
    ): Call<LoginResponse>

    companion object {
        operator fun invoke(): RetrofitInterface {
            return Retrofit.Builder()
                .baseUrl("https://jakartaqurban.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitInterface::class.java)
        }
    }
}
