package com.ilkom.vviped.model

import com.ilkom.vviped.model.login.LoginResponse
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
        @Part("selling_status") selling_status: RequestBody,
        @Part("whatsapp") whatsapp: RequestBody,
        @Part("user_id") user_id: Int,
        @Part("campaign_id") campaign_id: Int
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
        @Part("user_id") user_id: Int
    ): Call<UploadResponse>

    @Multipart
    @POST("api.php?apicall=sellingpost_profile")
    fun sellingPostProfile(
        @Part("user_id") user_id: Int
    ): Call<MutableList<SellingPostItem>>

    @Multipart
    @POST("api.php?apicall=delete_sellingpost")
    fun deleteProductProfile(
        @Part("id") id: Int,
    ): Call<MutableList<SellingPostItem>>

    @Multipart
    @POST("api.php?apicall=register")
    fun registerUser(
        @Part("email") email: RequestBody,
        @Part("fullname") fullname: RequestBody,
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
        @Part("user_profpic") user_profpic: RequestBody,

        ): Call<UploadResponse>

    @Multipart
    @POST("api.php?apicall=login")
    fun loginUser(
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
    ): Call<LoginResponse>

    @Multipart
    @POST("api.php?apicall=edituser")
    fun editUserProfile(
        @Part image: MultipartBody.Part,
        @Part("user_id") user_id: Int,
        @Part("fullname") fullname: RequestBody,
        @Part("username") username: RequestBody,
    ): Call<UpdateUserResponse>

    @Multipart
    @POST("api.php?apicall=useractivities")
    fun userActivities(
        @Part("user_id") user_id: Int,
        @Part("username") username: String,
        @Part("activity") activity: RequestBody
    ): Call<UploadResponse>

    @Multipart
    @POST("api.php?apicall=user")
    fun userLoggedIn(
        @Part image: MultipartBody.Part,
        @Part("id") id: RequestBody,
        @Part("email") email: RequestBody,
        @Part("fullname") fullname: RequestBody,
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
        @Part("user_profpic") user_profpic: RequestBody,
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
