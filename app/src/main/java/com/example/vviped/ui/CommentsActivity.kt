package com.example.vviped.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serius.CommentAPI
import com.example.vviped.LoginActivity
import com.example.vviped.R
import com.example.vviped.Retro
import com.example.vviped.comment_action
import com.example.vviped.model.Comment
import com.example.vviped.model.CommentAdapter
import com.example.vviped.model.CommentItem
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.comment_layout.*
import retrofit2.Call
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        val comments = listOf<CommentItem>(
            CommentItem(R.drawable.profilpic, "akunjahat", "jelek banget sih lo"),
            CommentItem(R.drawable.profilpic, "akunbaik", "cantik bgt!!"),
            CommentItem(R.drawable.profilpic, "okeshop", "kak kalo mau endorse chat kemana ya???")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recycleView_comments)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = CommentAdapter(this, comments)
    }
}

 fun getCommentAPI() {
     val retro = Retro().getRetroClientInstance().create(CommentAPI::class.java)
     retro.getComment().enqueue(object : retrofit2.Callback<List<Comment>> {
         override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
             val comment = response.body()
             for (c in comment!!) {
                 Log.e("Result : ", c.email.toString())
             }
         }

         override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
             Log.e("error", t.message.toString())
         }

     })
 }

