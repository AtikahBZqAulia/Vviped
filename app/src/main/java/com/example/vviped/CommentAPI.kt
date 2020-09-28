package com.example.serius

import retrofit2.Call
import com.example.vviped.Comment
import retrofit2.http.GET

interface CommentAPI {
    @GET("comments")
    fun getComment(): Call<List<Comment>>
}
