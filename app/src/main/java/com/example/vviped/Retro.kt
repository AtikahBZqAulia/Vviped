package com.example.vviped

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    fun getRetroClientInstance() : Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("jsonplaceholder.com/comment")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
