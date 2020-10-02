package com.example.vviped.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.ui.CommentsActivity
import com.example.vviped.R
import com.example.vviped.comment_action

class CommentAdapter(
    private val context : Context,
    private val comments : List <CommentItem>
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val prof_images = itemView.findViewById<ImageView>(R.id.comments_profpic)
        val uname_comments = itemView.findViewById<TextView>(R.id.comments_uname)
        val text_comments = itemView.findViewById<TextView>(R.id.comments_text)
        val comment_act = itemView.findViewById<ImageView>(R.id.comment_act)

        fun bindView(comment: CommentItem) {
            prof_images.setImageResource(comment.imagecomment)
            uname_comments.text = comment.unamecomment
            text_comments.text = comment.textcomment

            comment_act.setOnClickListener{
                val context = comment_act.context
                val intent = Intent(context, comment_action::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_layout, parent,false))

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(comments[position])
    }



}