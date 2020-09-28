package com.example.vviped.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vviped.LoginActivity
import com.example.vviped.R
import com.example.vviped.comment_action
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_landing.*

class CommentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        comment_act.setOnClickListener{
            startActivity(Intent(this, comment_action::class.java))
        }
    }
}