package com.example.serius

import retrofit2.Call
import com.example.serius.model.Comment
import retrofit2.http.GET

interface CommentAPI {
    @GET("comments")
    fun getComment(): Call<List<Comment>>
}