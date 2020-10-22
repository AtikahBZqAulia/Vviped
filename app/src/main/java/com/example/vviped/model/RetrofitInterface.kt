package com.example.vviped.model

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
    @POST("/images/upload")
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("desc") desc: RequestBody
    ): Call<UploadResponse>

    companion object {
        operator fun invoke(): RetrofitInterface {
            return Retrofit.Builder()
                .baseUrl("http://192.168.43.93:80/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitInterface::class.java)
        }
    }
}